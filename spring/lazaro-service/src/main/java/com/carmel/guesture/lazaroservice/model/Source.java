package com.carmel.guesture.lazaroservice.model;

import com.carmel.guesture.lazaroservice.request.SourceData;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "g_cupid_source")
@Audited
public class Source {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "medium")
    @Length(max = 250)
    private String medium;


    @Column(name = "point")
    @Length(max = 250)
    private String point;

    @Column(name = "is_synced")
    private Integer isSynced;

    @Column(name = "suite_id")
    @Length(max = 250)
    private String suiteId;


    public Source(){}

    public Source(SourceData source) {
        this.medium = source.getMedium();
        this.point = source.getPoint();
        this.isSynced = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
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
