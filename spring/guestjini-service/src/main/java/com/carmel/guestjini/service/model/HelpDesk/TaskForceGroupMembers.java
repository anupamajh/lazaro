package com.carmel.guestjini.service.model.HelpDesk;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_force_group_members")
@Audited
public class TaskForceGroupMembers {
    @Id
    @Column(name = "id")
    @Length(max = 40)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "client_id")
    @Length(max = 40)
    private String clientId;

    @Column(name = "org_id")
    @Length(max = 40)
    private String orgId;

    @Column(name = "task_force_group_id")
    private String taskForceGroupId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "is_group_admin")
    private String isGroupAdmin;

    @Column(name = "is_super_admin")
    private int isSuperAdmin;

    @Column(name = "created_by")
    @Length(max = 40)
    private String createdBy;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "last_modified_by")
    @Length(max = 40)
    private String lastModifiedBy;

    @Column(name = "last_Modified_time")
    private Date lastModifiedTime;

    @Column(name = "is_deleted")
    private int isDeleted;

    @Column(name = "deleted_by")
    @Length(max = 40)
    private String deletedBy;

    @Column(name = "deleted_time")
    private Date deletedTime;
}
