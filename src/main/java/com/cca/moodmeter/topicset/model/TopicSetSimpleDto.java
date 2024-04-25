package com.cca.moodmeter.topicset.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cca.moodmeter.topicoption.model.TopicOptionSimpleDto;

public class TopicSetSimpleDto implements Serializable {

    private Long id;

    private String type;

    private String question;

    private Integer order;

    private List<TopicOptionSimpleDto> options = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<TopicOptionSimpleDto> getOptions() {
        return options;
    }

    public void setOptions(List<TopicOptionSimpleDto> options) {
        this.options = options;
    }

}
