package com.cca.moodmeter.topic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
    private Integer status;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "creation_username")
    private String creationUsername;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_username")
    private String updateUsername;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TopicSetEntity> questions = new ArrayList<>();

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<TopicSetEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TopicSetEntity> questions) {
        this.questions = questions;
    }

}
