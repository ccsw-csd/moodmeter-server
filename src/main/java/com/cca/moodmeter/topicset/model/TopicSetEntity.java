package com.cca.moodmeter.topicset.model;

import java.util.ArrayList;
import java.util.List;

import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topicoption.model.TopicOptionEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "`topic_set`")
public class TopicSetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @Column(name = "type")
    private String type;

    @Column(name = "question")
    private String question;

    @Column(name = "`order`")
    private Integer order;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "set", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TopicOptionEntity> options = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
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

    public List<TopicOptionEntity> getOptions() {
        return options;
    }

    public void setOptions(List<TopicOptionEntity> options) {
        this.options = options;
    }

}
