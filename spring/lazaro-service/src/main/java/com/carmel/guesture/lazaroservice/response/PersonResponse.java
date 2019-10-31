package com.carmel.guesture.lazaroservice.response;

import com.carmel.guesture.lazaroservice.model.Person;

public class PersonResponse {

    private Person person;
    private Boolean success;
    private String error;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
