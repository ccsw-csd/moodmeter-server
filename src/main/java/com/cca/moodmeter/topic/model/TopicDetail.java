package com.cca.moodmeter.topic.model;

import java.util.List;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topicset.model.TopicSetDto;

public class TopicDetail {

    private TopicDto topic;

    private List<GroupDto> groups;

    private List<TopicSetDto> questions;

    public List<TopicSetDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<TopicSetDto> questions) {
        this.questions = questions;
    }

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

}
