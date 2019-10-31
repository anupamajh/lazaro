package com.carmel.guesture.lazaroservice.controller;

import com.carmel.guesture.lazaroservice.model.*;
import com.carmel.guesture.lazaroservice.response.PersonResponse;
import com.carmel.guesture.lazaroservice.response.PhonedResponse;
import com.carmel.guesture.lazaroservice.response.WebsiteResponse;
import com.carmel.guesture.lazaroservice.services.*;
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
    public PersonResponse added(@RequestBody Person person) {
        PersonResponse personResponse = new PersonResponse();
        try {
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
            tempPerson.setCupidId(tempPerson.getId());
            duplicatePerson = getPerson(tempPerson);
            tempPerson.setId(duplicatePerson.getId());
            personResponse.setPerson(personService.save(tempPerson));
            personResponse.setSuccess(true);
            personResponse.setError("");
        } catch (Exception ex) {
            personResponse.setSuccess(false);
            personResponse.setError(ex.getMessage());
        }
        return personResponse;
    }

    @RequestMapping(value = "/phoned", method = RequestMethod.POST)
    public PhonedResponse phoned(@RequestBody Phoned phoned) {
        PhonedResponse phonedResponse = new PhonedResponse();
        try {
            Phoned tempPhoned = phoned;
            if (phoned.getAgents() != null) {
                List<Agent> agentList = new ArrayList<>();
                Agent tempAgent;
                phoned.getAgents().forEach(agent -> {
                    agentList.add(getAgent(agent));
                });
                tempPhoned.setAgents(agentList);
            }
            tempPhoned.setCupidId(tempPhoned.getId());
            tempPhoned.setId(null);
            phonedResponse.setPhoned(phonedService.save(tempPhoned));
            phonedResponse.setSuccess(true);
            phonedResponse.setError("");
        } catch (Exception ex) {
            phonedResponse.setSuccess(false);
            phonedResponse.setError(ex.getMessage());
        }
        return phonedResponse;
    }

    @RequestMapping(value = "/on/website", method = RequestMethod.POST)
    public WebsiteResponse onWebsite(@RequestBody Website website) {
        WebsiteResponse websiteResponse = new WebsiteResponse();
        try {
            Website tempWebsite = website;
            tempWebsite.setCupidId(website.getCupidId());
            websiteResponse.setWebsite(websiteService.save(tempWebsite));
            websiteResponse.setSuccess(true);
            websiteResponse.setError("");
        } catch (Exception ex) {
            websiteResponse.setSuccess(false);
            websiteResponse.setError(ex.getMessage());
        }
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
