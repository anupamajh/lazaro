package com.carmel.guesture.lazaroservice.services;

import com.carmel.guesture.lazaroservice.model.Person;

import java.util.List;

public interface PersonService {
    Person save(Person person);
    List<Person> findAllByCupidId(String cupidId);
}
