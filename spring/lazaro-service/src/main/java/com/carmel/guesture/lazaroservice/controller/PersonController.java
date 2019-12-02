package com.carmel.guesture.lazaroservice.controller;

import com.carmel.guesture.lazaroservice.model.*;
import com.carmel.guesture.lazaroservice.request.PersonData;
import com.carmel.guesture.lazaroservice.request.PersonRequest;
import com.carmel.guesture.lazaroservice.request.PhonedRequest;
import com.carmel.guesture.lazaroservice.request.WebsiteRequest;
import com.carmel.guesture.lazaroservice.response.PersonResponse;
import com.carmel.guesture.lazaroservice.response.PhonedResponse;
import com.carmel.guesture.lazaroservice.response.WebsiteResponse;
import com.carmel.guesture.lazaroservice.services.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    AgentService agentService;

    @Autowired
    SourceService sourceService;

    @Autowired
    PersonService personService;

    @Autowired
    PhonedService phonedService;

    @Autowired
    WebsiteService websiteService;

    @RequestMapping(value = "/added", method = RequestMethod.POST)
    public PersonResponse added(@RequestBody PersonRequest personRequest) {
        Person person = new Person(personRequest.getData());
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PersonResponse personResponse = new PersonResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(personRequest));
            Person tempPerson = person;
            Person duplicatePerson = person;
            if (person.getAgents() != null) {
                List<Agent> agentList = new ArrayList<>();
                Agent tempAgent;
                person.getAgents().forEach(agent -> {
                    agentList.add(getAgent(agent));
                });
                tempPerson.setAgents(agentList);
            }
            if (person.getSources() != null) {
                List<Source> sourceList = new ArrayList<>();
                Source tempSource;
                person.getSources().forEach(source -> {
                    sourceList.add(getSource(source));
                });
                tempPerson.setSources(sourceList);
            }
            //tempPerson.setCupidId(tempPerson.getId());
            duplicatePerson = getPerson(tempPerson);
            tempPerson.setId(duplicatePerson.getId());
            personResponse.setPerson(personService.save(tempPerson));
            personResponse.setSuccess(true);
            personResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            personResponse.setSuccess(false);
            personResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");

        return personResponse;
    }

    @RequestMapping(value = "/phoned", method = RequestMethod.POST)
    public PhonedResponse phoned(@RequestBody PhonedRequest phonedRequest) {
        Phoned phoned = new Phoned(phonedRequest.getData());
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        PhonedResponse phonedResponse = new PhonedResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(phonedRequest));
            Phoned tempPhoned = phoned;
            if (phoned.getAgents() != null) {
                List<Agent> agentList = new ArrayList<>();
                Agent tempAgent;
                phoned.getAgents().forEach(agent -> {
                    agentList.add(getAgent(agent));
                });
                tempPhoned.setAgents(agentList);
            }
            tempPhoned.setId(null);
            phonedResponse.setPhoned(phonedService.save(tempPhoned));
            phonedResponse.setSuccess(true);
            phonedResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            phonedResponse.setSuccess(false);
            phonedResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return phonedResponse;
    }

    @RequestMapping(value = "/on/website", method = RequestMethod.POST)
    public WebsiteResponse onWebsite(@RequestBody WebsiteRequest websiteRequest) {
        Website website = new Website(websiteRequest.getData());
        ObjectMapper objectMapper = new ObjectMapper();
        logger.trace("Entering");
        WebsiteResponse websiteResponse = new WebsiteResponse();
        try {
            logger.trace("Data:{}", objectMapper.writeValueAsString(websiteRequest));
            Website tempWebsite = website;
            tempWebsite.setCupidId(website.getCupidId());
            websiteResponse.setWebsite(websiteService.save(tempWebsite));
            websiteResponse.setSuccess(true);
            websiteResponse.setError("");
            logger.trace("Completed Successfully");
        } catch (Exception ex) {
            logger.error(ex.getMessage(),ex);
            websiteResponse.setSuccess(false);
            websiteResponse.setError(ex.getMessage());
        }
        logger.trace("Exiting");
        return websiteResponse;
    }

    private Agent getAgent(Agent agent) {
        Agent returnAgent;
        List<Agent> agentList = agentService.findAllByNameAndPhoneNumber(agent.getName(), agent.getPhoneNumber());
        if (agentList.size() > 0) {
            returnAgent = agentList.get(0);
        } else {
            returnAgent = agentService.save(agent);
        }
        returnAgent.setIsSynced(0);
        return returnAgent;
    }

    private Source getSource(Source source) {
        Source returnSource;
        List<Source> sourceList = sourceService.findAllByMediumAndPoint(source.getMedium(), source.getPoint());
        if (sourceList.size() > 0) {
            returnSource = sourceList.get(0);
        } else {
            returnSource = sourceService.save(source);
        }
        return returnSource;
    }

    private Person getPerson(Person person) {
        Person returnPerson;
        List<Person> personList = personService.findAllByCupidId(person.getCupidId());
        if (personList.size() > 0) {
            returnPerson = personList.get(0);
        } else {
            returnPerson = new Person();
        }
        return returnPerson;
    }
}
