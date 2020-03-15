package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Person;
import com.carmel.guesture.lazaroservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Person> findAllBySyncStatusIsNot(int isSynced) {
        return personRepository.findAllByIsSyncedIsNot(isSynced);
    }

    @Override
    public List<Person> findAllBySuiteId(String suiteId) {
        return personRepository.findAllBySuiteId(suiteId);
    }

    @Override
    public Optional<Person> findByCupidId(String cupidId) {
        return personRepository.findByCupidId(cupidId);
    }
}
