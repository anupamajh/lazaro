package com.carmel.common.dbservice.Base.Client.Controller;

import com.carmel.common.dbservice.Base.Client.Model.Client;
import com.carmel.common.dbservice.Base.Client.Request.ClientRequest;
import com.carmel.common.dbservice.Base.Client.Responce.ClientResponse;
import com.carmel.common.dbservice.Base.Client.Service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    ClientService clientService;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ClientResponse register(@Valid @RequestBody Client client) {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try{
            clientResponse = clientService.register(client);
        }catch (Exception ex){
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        return clientResponse;

    }

    @RequestMapping(value = "/register-client", method = RequestMethod.POST)
    public ClientResponse registerClient(@Valid @RequestBody ClientRequest clientRequest) {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try{
            clientResponse = clientService.registerClient(clientRequest);
        }catch (Exception ex){
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        return clientResponse;

    }

    @RequestMapping(value = "/trash", method = RequestMethod.POST)
    public ClientResponse moveToTrash(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try {
            clientResponse = clientService
                    .moveToTrash(formData);
        } catch (Exception ex) {
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return clientResponse;
    }

    @RequestMapping(value = "/get-all", method = RequestMethod.GET)
    public ClientResponse getAll() {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try{
            clientResponse = clientService.getAll();
        }catch (Exception ex){
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        return clientResponse;
    }

    @RequestMapping(value = "/get-clients", method = RequestMethod.POST)
    public ClientResponse getPaginated(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try {
            clientResponse = clientService
                    .getPaginated(formData);
        } catch (Exception ex) {
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return clientResponse;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ClientResponse get(@RequestBody Map<String, String> formData) {
        logger.trace("Entering");
        ClientResponse clientResponse = new ClientResponse();
        try {
            clientResponse = clientService
                    .get(formData);
        } catch (Exception ex) {
            clientResponse.setSuccess(true);
            clientResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return clientResponse;
    }

}
