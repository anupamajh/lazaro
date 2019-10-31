package com.carmel.guesture.lazaroservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_cupid_person_on_website")
public class Website {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "`when`")
    @Length(max = 250)
    private String when;

    @Column(name = "client")
    @Length(max = 250)
    private String client;

    @Column(name = "phone_number")
    @Length(max = 250)
    private String phoneNumber;

    @Column(name = "cupid_id")
    @Length(max = 250)
    private String cupidId;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "`where`", columnDefinition = "TEXT")
    private String where;

    @Column(name = "interests", columnDefinition = "TEXT")
    private String[] interests;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCupidId() {
        return cupidId;
    }

    public void setCupidId(String cupidId) {
        this.cupidId = cupidId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }
}
