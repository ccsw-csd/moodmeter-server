package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.topic.model.TopicGroupEntity;

public interface TopicGroupRepository extends CrudRepository<TopicGroupEntity, Long> {

    List<TopicGroupEntity> findGroupsByTopicId(Long id);

    void deleteByTopicId(Long id);

    List<TopicGroupEntity> findAllByGroupIn(List<GroupEntity> groups);

}
