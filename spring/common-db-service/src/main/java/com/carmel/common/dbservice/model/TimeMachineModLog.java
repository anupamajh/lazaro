package com.carmel.common.dbservice.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "g_timemachine_modlog")
@Audited
public class TimeMachineModLog {

    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "application_id")
    @Length(max = 40)
    private String applicationId;

    @Column(name = "record_id")
    @Length(max = 40)
    private String recordId;

    @Column(name = "record_type")
    @Length(max = 64)
    private String recordType;

    @Column(name = "record_backend")
    @Length(max = 64)
    private String recordBackend;

    @Column(name = "modification_time")
    private Date modificationTime;


    @Column(name = "modification_account")
    @Length(max = 40)
    private String modificationAccount;

    @Column(name = "modified_attribute")
    @Length(max = 64)
    private String modifiedAttribute;

    @Column(name = "old_value", columnDefinition = "TEXT")
    @Length(max = 64)
    private String oldValue;

    @Column(name = "new_value", columnDefinition = "TEXT")
    @Length(max = 64)
    private String newValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getRecordBackend() {
        return recordBackend;
    }

    public void setRecordBackend(String recordBackend) {
        this.recordBackend = recordBackend;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public void setModificationTime(Date modificationTime) {
        this.modificationTime = modificationTime;
    }

    public String getModificationAccount() {
        return modificationAccount;
    }

    public void setModificationAccount(String modificationAccount) {
        this.modificationAccount = modificationAccount;
    }

    public String getModifiedAttribute() {
        return modifiedAttribute;
    }

    public void setModifiedAttribute(String modifiedAttribute) {
        this.modifiedAttribute = modifiedAttribute;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
