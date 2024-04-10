package com.cca.moodmeter.topic.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`topic`")
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "visits")
    private Long visits;

    @Column(name = "status")
    private boolean status;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "creation_username")
    private String creationUsername;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_username")
    private String updateUsername;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getVisits() {
        return visits;
    }

    public void setVisits(Long visits) {
        this.visits = visits;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreationUsername() {
        return creationUsername;
    }

    public void setCreationUsername(String creationUsername) {
        this.creationUsername = creationUsername;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername;
    }

}
