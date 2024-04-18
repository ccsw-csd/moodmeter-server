package com.cca.moodmeter.topicgroup;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;

public interface TopicGroupRepository extends CrudRepository<TopicGroupEntity, Long> {

    List<TopicGroupEntity> findGroupsByTopicId(Long id);

    void deleteByTopicId(Long id);

}
