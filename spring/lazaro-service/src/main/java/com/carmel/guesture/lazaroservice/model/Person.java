package com.carmel.guesture.lazaroservice.model;

import com.carmel.guesture.lazaroservice.request.PersonData;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "g_cupid_person")
@Audited
public class Person {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "cupid_id")
    @Length(max = 250)
    private String cupidId;

    @Column(name = "`when`")
    @Length(max = 250)
    private String when;

    @Column(name = "client")
    @Length(max = 250)
    private String client;

    @Column(name = "phone_number")
    @Length(max = 250)
    private String phoneNumber;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "name")
    @Length(max = 250)
    private String name;

    @Column(name = "interests", columnDefinition = "TEXT")
    private String[] interests;

    @Column(name = "email_addresses", columnDefinition = "TEXT")
    private String[] emailAddresses;

    @Column(name = "missed")
    private boolean missed;

    @Column(name = "startTime")
    @Length(max = 250)
    private String startTime;

    @Column(name = "endTime")
    @Length(max = 250)
    private String endTime;

    @Column(name = "duration")
    private int duration;

    @Column(name = "recording_url", columnDefinition = "TEXT")
    private String recordingURL;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "g_cupid_person_agent",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "agent_id", referencedColumnName = "id")
            }
    )
    private List<Agent> agents;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "g_cupid_person_source",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "source_id", referencedColumnName = "id")
            }
    )
    private List<Source> sources;

    public Person(PersonData personData) {
        this.cupidId = personData.getId();
        this.when = personData.getWhen();
        this.client = personData.getClient();
        this.phoneNumber = personData.getPhoneNumber();
        this.verified = personData.isVerified();
        this.sources = new ArrayList<>();
        if (personData.getSource() != null)
            this.sources.add(new Source(personData.getSource()));
        this.interests = personData.getInterests();
        this.emailAddresses = personData.getEmailAddresses();
        this.missed = personData.isMissed();
        this.agents = new ArrayList<>();
        if (personData.getAgent() != null)
            this.agents.add(new Agent(personData.getAgent()));
        this.startTime = personData.getStartTime();
        this.endTime = personData.getEndTime();
        this.duration = personData.getDuration();
        this.recordingURL = personData.getRecordingURL();
        this.name = personData.getName();
    }

    public Person() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCupidId() {
        return cupidId;
    }

    public void setCupidId(String cupidId) {
        this.cupidId = cupidId;
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String[] getEmailAddresses() {
        return emailAddresses;
    }

    public void setEmailAddresses(String[] emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public boolean isMissed() {
        return missed;
    }

    public void setMissed(boolean missed) {
        this.missed = missed;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getRecordingURL() {
        return recordingURL;
    }

    public void setRecordingURL(String recordingURL) {
        this.recordingURL = recordingURL;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents) {
        this.agents = agents;
    }

    public List<Source> getSources() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
