package com.carmel.guesture.lazaroservice.model;

import com.carmel.guesture.lazaroservice.request.AgentData;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_cupid_agent")
@Audited
public class Agent {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "name")
    @Length(max = 250)
    private String name;

    @Column(name = "phone_number")
    @Length(max = 250)
    private String phoneNumber;

    @Column(name = "is_synced")
    private Integer isSynced = 0;

    @Column(name = "suite_id")
    @Length(max = 250)
    private String suiteId;

    public Agent(){this.isSynced = 0;}

    public Agent(AgentData agent) {
        this.name = agent.getName();
        this.phoneNumber = agent.getPhoneNumber();
        this.isSynced = 0;
    }

    public Agent(CRMUser crmUser){
        this.name = crmUser.getAttributes().getFull_name();
        this.phoneNumber = crmUser.getAttributes().getPhone_mobile();
        this.suiteId = crmUser.getId();
        this.isSynced = 1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIsSynced() {
        return isSynced;
    }

    public void setIsSynced(Integer isSynced) {
        this.isSynced = isSynced;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }
}
