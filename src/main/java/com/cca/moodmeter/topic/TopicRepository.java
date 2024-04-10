package com.cca.moodmeter.topic;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topic.model.TopicEntity;

public interface TopicRepository extends CrudRepository<TopicEntity, Long> {

}
