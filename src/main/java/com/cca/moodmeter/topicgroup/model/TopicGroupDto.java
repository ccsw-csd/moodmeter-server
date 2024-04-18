package com.cca.moodmeter.topicgroup.model;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topic.model.TopicDto;

public class TopicGroupDto {

    private Long id;

    private TopicDto topic;

    private GroupDto group;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicDto getTopic() {
        return topic;
    }

    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }

    public GroupDto getGroup() {
        return group;
    }

    public void setGroup(GroupDto group) {
        this.group = group;
    }

}
