package com.carmel.guesture.lazaroservice.components;

import com.carmel.guesture.lazaroservice.model.*;
import com.carmel.guesture.lazaroservice.request.CRMLeadData;
import com.carmel.guesture.lazaroservice.request.CRMTaskData;
import com.carmel.guesture.lazaroservice.response.CRMLeadsResponse;
import com.carmel.guesture.lazaroservice.response.CRMUsersResponse;
import com.carmel.guesture.lazaroservice.response.PhonedResponse;
import com.carmel.guesture.lazaroservice.services.PersonService;
import com.carmel.guesture.lazaroservice.services.PhonedService;
import com.carmel.guesture.lazaroservice.services.WebsiteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class SuiteCRMTasks {

    @Autowired
    PersonService personService;

    @Autowired
    PhonedService phonedService;

    @Autowired
    WebsiteService websiteService;

    @Autowired
    YAMLConfig yamlConfig;

    @Autowired
    RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(SuiteCRMTasks.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");


    @Scheduled(fixedRate = 30 * 1000)
    public void UploadLeads() {
        String leadPostURL = yamlConfig.getCrmURL();
        List<Person> personList = personService.findAllBySyncStatusIsNot(1);
        if (personList.size() > 0) {
            for (Person person : personList) {
                try {
                    if (person.getAgents().size() > 0) {
                        CRMUser crmUser = getCRMUserByPhoneNumber(person.getAgents().get(0).getPhoneNumber());
                        CRMLead crmExistingLead = this.getCRMLeadByCupidId(person.getPhoneNumber());

                        CRMLead crmLead = new CRMLead(person, crmUser);
                        CRMLeadData data = new CRMLeadData();
                        if (crmExistingLead != null) {
                            crmLead.setId(crmExistingLead.getId());
                            data.setData(crmLead);
                            ObjectMapper objectMapper = new ObjectMapper();
                            try {
                                logger.trace("Data:{}", objectMapper.writeValueAsString(data));
                            } catch (Exception ex) {

                            }
                            //String leadResponse = restTemplate.patchForObject(leadPostURL, data, String.class);
                        } else {
                            data.setData(crmLead);
                            ResponseEntity<String> leadResponse = restTemplate.postForEntity(leadPostURL, data, String.class);
                        }
                        person.setIsSynced(1);
                        personService.save(person);
                    }

                } catch (Exception ex) {
                    person.setIsSynced(2);
                    personService.save(person);
                }
            }
        }
        this.UploadLeadTasks();
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

    }

    public void UploadLeadTasks() {
        String postURL = yamlConfig.getCrmURL();
        List<Phoned> phonedList = phonedService.findAllBySyncStatusIsNot(1);
        if (phonedList.size() > 0) {
            for (Phoned phoned : phonedList) {
                try {
                    CRMLead crmLead = this.getCRMLeadByCupidId(phoned.getPhoneNumber());
                    if (crmLead != null) {
                        CRMTasks tasks = new CRMTasks(phoned, crmLead);
                        CRMTaskData data = new CRMTaskData();
                        data.setData(tasks);
                        ResponseEntity<String> leadResponse = restTemplate.postForEntity(postURL, data, String.class);
                        phoned.setIsSynced(1);
                        phonedService.save(phoned);
                    }else{
                        phoned.setIsSynced(null);
                        phonedService.save(phoned);
                    }

                }catch(Exception ex){
                    phoned.setIsSynced(2);
                    phonedService.save(phoned);
                }
            }

        }

        List<Website> websiteList = websiteService.findAllBySyncStatusIsNot(1);
        if(websiteList.size() > 0){
            for (Website website : websiteList) {
                try {
                    CRMLead crmLead = this.getCRMLeadByCupidId(website.getPhoneNumber());
                    if (crmLead != null) {
                        CRMTasks tasks = new CRMTasks(website, crmLead);
                        CRMTaskData data = new CRMTaskData();
                        data.setData(tasks);
                        ResponseEntity<String> leadResponse = restTemplate.postForEntity(postURL, data, String.class);
                        website.setIsSynced(1);
                        websiteService.save(website);
                    }else{
                        website.setIsSynced(null);
                        websiteService.save(website);
                    }

                }catch(Exception ex){
                    website.setIsSynced(2);
                    websiteService.save(website);
                }
            }
        }

    }

    private CRMUser getCRMUserByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        String url = yamlConfig.getCrmURL();
        url += "/User?filter[operator]=or&filter[phone_mobile][eq]="
                + phoneNumber.trim() + "&filter[phone_work][eq]="
                + phoneNumber.trim() + "&filter[phone_other][eq]="
                + phoneNumber.trim();
        CRMUsersResponse crmUsersResponse = restTemplate.getForObject(url,
                CRMUsersResponse.class);
        for (CRMUser user : crmUsersResponse.getData()) {
            if (user.getAttributes().getPhone_mobile().trim().equals(phoneNumber.trim())) {
                return user;
            }
            if (user.getAttributes().getPhone_work().trim().equals(phoneNumber.trim())) {
                return user;
            }

            if (user.getAttributes().getPhone_other().trim().equals(phoneNumber.trim())) {
                return user;
            }
        }
        return null;

    }

    public CRMLead getCRMLeadByCupidId(String phoneNumber) {
        String url = yamlConfig.getCrmURL();
        url += "/Leads?filter[operator]=or&filter[phone_mobile][eq]="
                + phoneNumber.trim();
        CRMLeadsResponse crmLeadsResponse = restTemplate.getForObject(url,
                CRMLeadsResponse.class);
        for (CRMLead lead : crmLeadsResponse.getData()) {
            if (lead.getAttributes().getPhone_mobile().trim().equals(phoneNumber)) {
                return lead;
            }
        }
        return null;
    }

}
