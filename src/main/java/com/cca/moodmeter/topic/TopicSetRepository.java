package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topic.model.TopicSetEntity;

public interface TopicSetRepository extends CrudRepository<TopicSetEntity, Long> {

    List<TopicSetEntity> findByTopicId(Long topicId);

}
