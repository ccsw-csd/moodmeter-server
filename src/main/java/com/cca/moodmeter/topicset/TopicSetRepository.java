package com.cca.moodmeter.topicset;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topicset.model.TopicSetEntity;

public interface TopicSetRepository extends CrudRepository<TopicSetEntity, Long> {

    List<TopicSetEntity> findAllByTopicId(Long id);

}
