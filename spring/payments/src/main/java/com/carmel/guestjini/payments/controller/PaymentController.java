package com.carmel.guestjini.payments.controller;

import com.carmel.guestjini.payments.components.MailClient;
import com.carmel.guestjini.payments.config.PaytmDetails;
import com.carmel.guestjini.payments.model.PaymentStatus;
import com.carmel.guestjini.payments.service.PaymentStatusService;
import com.paytm.pg.merchant.CheckSumServiceHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Controller
public class PaymentController {

    @Autowired
    private PaytmDetails paytmDetails;

    @Autowired
    private Environment env;

    @Autowired
    PaymentStatusService paymentStatusService;

    @Autowired
    MailClient mailClient;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/pgredirect")
    public ModelAndView getRedirect(
            @RequestParam(name = "CUST_ID") String customerId,
            @RequestParam(name = "EMAIL") String email,
            @RequestParam(name = "TXN_AMOUNT") String transactionAmount
    ) throws Exception {
        ModelAndView modelAndView = new ModelAndView("redirect:" + paytmDetails.getPaytmUrl());
        String orderId = String.valueOf(System.nanoTime());
        TreeMap<String, String> parameters = new TreeMap<>();
        paytmDetails.getDetails().forEach((k, v) -> parameters.put(k, v));
        parameters.put("ORDER_ID", orderId);
        parameters.put("TXN_AMOUNT", transactionAmount);
        parameters.put("CUST_ID", customerId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        PaymentStatus paymentStatus = new PaymentStatus();
        paymentStatus.setId(orderId);
        paymentStatus.setCustomerId(customerId);
        paymentStatus.setOrderId(orderId);
        paymentStatus.setEmail(email);
        paymentStatusService.save(paymentStatus);
        modelAndView.addAllObjects(parameters);
        return modelAndView;
    }

    @GetMapping(value = "/check-status")
    public String checkStatus() {
        return "check-status";
    }

    @PostMapping(value = "/check-status-result")
    public String checkStatusResult(
            @RequestParam(name = "ORDERID") String orderId
    ) throws Exception {
        TreeMap<String, String> parameters = new TreeMap<>();
        parameters.put("MID", paytmDetails.getMerchantId());
        parameters.put("ORDERID", orderId);
        String checkSum = getCheckSum(parameters);
        parameters.put("CHECKSUMHASH", checkSum);
        final String uri = paytmDetails.getPaytmStatusUrl();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
       JSONObject postData = new JSONObject();
        postData.put("MID", paytmDetails.getMerchantId());
        postData.put("ORDERID", orderId);
        postData.put("CHECKSUMHASH", checkSum);
        HttpEntity<String> entity = new HttpEntity<>(postData.toString(), headers);
        ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        System.out.println(result);
        return "check-status-result";
    }

    @PostMapping(value = "/pgresponse")
    public String getResponseRedirect(HttpServletRequest request, Model model) {

        Map<String, String[]> mapData = request.getParameterMap();
        TreeMap<String, String> parameters = new TreeMap<String, String>();
        mapData.forEach((key, val) -> parameters.put(key, val[0]));
        String paytmChecksum = "";
        if (mapData.containsKey("CHECKSUMHASH")) {
            paytmChecksum = mapData.get("CHECKSUMHASH")[0];
        }
        String result;
        Optional<PaymentStatus> optionalPaymentStatus = paymentStatusService.findById(parameters.get("ORDERID"));

        boolean isValideChecksum = false;
        System.out.println("RESULT : " + parameters.toString());
        try {
            isValideChecksum = validateCheckSum(parameters, paytmChecksum);
            if (isValideChecksum && parameters.containsKey("RESPCODE")) {
                if (parameters.get("RESPCODE").equals("01")) {
                    result = "Payment Successful";
                    TreeMap<String, String> newParamaters = new TreeMap<>();
                    newParamaters.put("MID", paytmDetails.getMerchantId());
                    newParamaters.put("ORDERID", parameters.get("ORDERID"));
                    String checkSum = getCheckSum(parameters);
                    newParamaters.put("CHECKSUMHASH", checkSum);
                    final String uri = paytmDetails.getPaytmStatusUrl();
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    JSONObject postData = new JSONObject();
                    postData.put("MID", paytmDetails.getMerchantId());
                    postData.put("ORDERID", newParamaters.get("ORDERID"));
                    postData.put("CHECKSUMHASH", checkSum);
                    HttpEntity<String> entity = new HttpEntity<>(postData.toString(), headers);
                    ResponseEntity<String> result1 = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
                } else {
                    result = "Payment Failed";
                }
            } else {
                result = "Checksum mismatched";
            }
        } catch (Exception e) {
            if(optionalPaymentStatus.isPresent()){
                PaymentStatus paymentStatus = optionalPaymentStatus.get();
                paymentStatus.setBankName(parameters.get("BANKNAME"));
                paymentStatus.setBankTxnId(parameters.get("BANKTXNID"));
                paymentStatus.setCurrency(parameters.get("CURRENCY"));
                paymentStatus.setGateway(parameters.get("GATEWAYNAME"));
                paymentStatus.setMid(parameters.get("MID"));
                paymentStatus.setPaymentMode(parameters.get("PAYMENTMODE"));
                paymentStatus.setRespCode(parameters.get("RESPCODE"));
                paymentStatus.setRespMsg(parameters.get("RESPMSG"));
                paymentStatus.setStatus(parameters.get("STATUS")); paymentStatus.setIsComplete(1);
                paymentStatusService.save(paymentStatus);
            }
            result = e.toString();
        }
        model.addAttribute("result", result);
         if(optionalPaymentStatus.isPresent()){
            PaymentStatus paymentStatus = optionalPaymentStatus.get();
            paymentStatus.setBankName(parameters.get("BANKNAME"));
            paymentStatus.setBankTxnId(parameters.get("BANKTXNID"));
            paymentStatus.setCurrency(parameters.get("CURRENCY"));
            paymentStatus.setGateway(parameters.get("GATEWAYNAME"));
            paymentStatus.setMid(parameters.get("MID"));
            paymentStatus.setPaymentMode(parameters.get("PAYMENTMODE"));
            paymentStatus.setRespCode(parameters.get("RESPCODE"));
            paymentStatus.setRespMsg(parameters.get("RESPMSG"));
            paymentStatus.setStatus(parameters.get("STATUS"));
            paymentStatus.setTxnAmount(parameters.get("TXNAMOUNT"));
            paymentStatus.setTxnDate(parameters.get("TXNDATE"));
            paymentStatus.setTxnId(parameters.get("TXNID"));
            paymentStatus.setIsComplete(1);
            paymentStatusService.save(paymentStatus);
        }
        parameters.remove("CHECKSUMHASH");
        parameters.remove("MID");
        model.addAttribute("parameters", parameters);
        mailClient.sendPaymentResponse(optionalPaymentStatus.get().getEmail(),parameters);
        return "pgresponse";
    }

    private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
        return CheckSumServiceHelper.getCheckSumServiceHelper().verifycheckSum(paytmDetails.getMerchantKey(),
                parameters, paytmChecksum);
    }


    private String getCheckSum(TreeMap<String, String> parameters) throws Exception {
        return CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(paytmDetails.getMerchantKey(), parameters);
    }

}
