package com.cca.moodmeter.topic.model;

import java.util.Date;
import java.util.List;

import com.cca.moodmeter.group.model.GroupDto;

public class TopicDetail {

    private TopicDto topic;

    private List<GroupDto> groups;

    private List<TopicAdminDto> admins;

    private Integer votes;

    private Date userVotingDate;

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }

    public List<GroupDto> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupDto> groups) {
        this.groups = groups;
    }

    public List<TopicAdminDto> getAdmins() {
        return admins;
    }

    public void setAdmins(List<TopicAdminDto> admins) {
        this.admins = admins;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Date getUserVotingDate() {
        return userVotingDate;
    }

    public void setUserVotingDate(Date userVotingDate) {
        this.userVotingDate = userVotingDate;
    }

}
