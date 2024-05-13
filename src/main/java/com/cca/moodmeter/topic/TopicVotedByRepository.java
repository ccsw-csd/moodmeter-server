package com.cca.moodmeter.topic;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topic.model.TopicVotedByEntity;

public interface TopicVotedByRepository extends CrudRepository<TopicVotedByEntity, Long> {

    boolean existsByTopicIdAndPersonId(Long id, Long personId);

}
