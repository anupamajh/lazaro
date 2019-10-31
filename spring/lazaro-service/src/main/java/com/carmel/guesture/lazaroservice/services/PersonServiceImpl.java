package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Person;
import com.carmel.guesture.lazaroservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired
    PersonRepository personRepository;

    @Override
    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findAllByCupidId(String cupidId) {
        return personRepository.findAllByCupidId(cupidId);
    }
}
