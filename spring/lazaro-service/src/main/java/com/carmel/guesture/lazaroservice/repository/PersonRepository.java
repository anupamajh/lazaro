package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository  extends JpaRepository<Person, String> {
    List<Person> findAllByCupidId(String cupidId);
}
