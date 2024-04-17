package com.cca.moodmeter.topicgroup;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;

public interface TopicGroupRepository extends CrudRepository<TopicGroupEntity, Long> {

    List<GroupEntity> findGroupsByTopicId(Long id);

}
