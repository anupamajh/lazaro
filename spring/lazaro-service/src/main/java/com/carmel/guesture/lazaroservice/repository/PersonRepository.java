package com.carmel.guesture.lazaroservice.repository;

import com.carmel.guesture.lazaroservice.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository  extends JpaRepository<Person, String> {
    List<Person> findAllByCupidId(String cupidId);

    List<Person> findAllByIsSyncedIsNot(int isSynced);

    List<Person> findAllBySuiteId(String suiteId);

    Optional<Person> findByCupidId(String cupidId);
}
