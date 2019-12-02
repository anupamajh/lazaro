package com.carmel.guesture.lazaroservice.model;

public class CRMTasksAttributes {
    private String name;
    private String description;
    private String parent_type;
    private String parent_id;
    private String priority;
    private String date_start;
    private String date_due;
    private String status;


    public CRMTasksAttributes(Phoned phoned, CRMLead crmLead) {
        this.name = "Phone call received ";
        this.description = (phoned.getRecordingURL() == null ?
                "No Recording URL" : phoned.getRecordingURL())
                + "\n" + "Phone call received - Starting From  "
                + phoned.getStartTime() + " - TO - " + phoned.getEndTime()
                + " For duration " + phoned.getDuration() + " seconds";
        this.parent_type = "Leads";
        this.parent_id = crmLead.getId();
        this.priority = "High";
        this.date_start = phoned.getWhen();
        this.date_due = phoned.getEndTime();
        this.status = "Completed";
    }

    public CRMTasksAttributes(Website website, CRMLead crmLead) {
        this.name = "WebSite Visited ";
        String interests = "";
        if (website.getInterests() != null)
            if (website.getInterests().length > 0) {
                interests = " showing interests on " + String.join(",", website.getInterests()) + " Sections";
            }
        if(website.getWhere() != null){
            interests = "Visited " + website.getWhere();
        }
        this.description =   interests;
        this.parent_type = "Leads";
        this.parent_id = crmLead.getId();
        this.priority = "High";
        this.date_start = website.getWhen();
        this.status = "Completed";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParent_type() {
        return parent_type;
    }

    public void setParent_type(String parent_type) {
        this.parent_type = parent_type;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDate_due() {
        return date_due;
    }

    public void setDate_due(String date_due) {
        this.date_due = date_due;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
