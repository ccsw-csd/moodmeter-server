package com.cca.moodmeter.topic.model;

import java.util.Date;

public class TopicItemListDto {

    private Long id;

    private String title;

    private Long visits;

    private Integer status;

    private Date creationDate;

    private Date closeDate;
    private String creationUsername;

    private Date updateDate;

    private String updateUsername;

    private Integer background;

    private Boolean votedByUser;

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

    public Integer getBackground() {
        return background;
    }

    public void setBackground(Integer background) {
        this.background = background;
    }

    public Boolean getVotedByUser() {
        return votedByUser;
    }

    public void setVotedByUser(Boolean votedByUser) {
        this.votedByUser = votedByUser;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }
}
