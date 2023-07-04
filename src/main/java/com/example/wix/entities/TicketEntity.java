package com.example.wix.entities;


import java.util.List;

public class TicketEntity {
    private String id;
    private String title;
    private String content;
    private String userEmail;
    private long creationTime;
    private List<String> labels;
    public TicketEntity(){

    }
    public TicketEntity(String id, String title, String content, String userEmail, long creationTime, List<String> labels) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userEmail = userEmail;
        this.creationTime = creationTime;
        this.labels = labels;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(long creationTime) {
        this.creationTime = creationTime;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }
}
