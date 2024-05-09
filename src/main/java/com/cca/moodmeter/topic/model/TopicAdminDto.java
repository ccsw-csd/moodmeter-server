package com.cca.moodmeter.topic.model;

import com.cca.moodmeter.person.model.PersonDto;

public class TopicAdminDto {

    private Long id;

    private TopicDto topic;

    private PersonDto person;

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

    public PersonDto getPerson() {
        return person;
    }

    public void setPerson(PersonDto person) {
        this.person = person;
    }

}
