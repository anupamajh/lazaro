package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Person save(Person person);
    List<Person> findAllByCupidId(String cupidId);

    List<Person> findAllBySyncStatusIsNot(int isSynced);

    List<Person> findAllBySuiteId(String suiteId);

    Optional<Person> findByCupidId(String cupidId);
}
