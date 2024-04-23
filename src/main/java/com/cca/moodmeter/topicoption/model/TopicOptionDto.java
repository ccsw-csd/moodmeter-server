package com.cca.moodmeter.topicoption.model;

import com.cca.moodmeter.topicset.model.TopicSetDto;

public class TopicOptionDto {

    private Long id;

    private TopicSetDto set;

    private String image;

    private String name;

    private Integer order;

    private Integer votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicSetDto getSet() {
        return set;
    }

    public void setSet(TopicSetDto set) {
        this.set = set;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

}
