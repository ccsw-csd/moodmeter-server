package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topic.model.TopicVotedByEntity;

public interface TopicVotedByRepository extends CrudRepository<TopicVotedByEntity, Long> {

    boolean existsByTopicIdAndPersonId(Long id, Long personId);

    List<TopicVotedByEntity> findAllByTopicId(Long id);

    TopicVotedByEntity findByTopicIdAndPersonId(Long topicId, Long personId);

}
