package com.carmel.guesture.lazaroservice.model;

import com.carmel.guesture.lazaroservice.request.PhonedData;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "g_cupid_person_phoned")
@Audited
public class Phoned {
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
    @JoinTable(name = "g_cupid_person_phoned_agent",
            joinColumns = {
                    @JoinColumn(name = "person_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "agent_id", referencedColumnName = "id")
            }
    )
    private List<Agent> agents;

    public Phoned() {
    }

    public Phoned(PhonedData data) {
        this.cupidId = data.getId();
        this.when = data.getWhen();
        this.client = data.getClient();
        this.phoneNumber = data.getPhoneNumber();
        this.verified = data.isVerified();
        this.missed = data.isMissed();
        this.agents = new ArrayList<>();
        if (data.getAgent() != null)
            this.agents.add(new Agent(data.getAgent()));
        this.startTime = data.getStartTime();
        this.endTime = data.getEndTime();
        this.duration = data.getDuration();
        this.recordingURL = data.getRecordingURL();
    }


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
}
