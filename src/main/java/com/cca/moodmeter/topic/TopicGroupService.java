package com.cca.moodmeter.topic;

import java.util.List;

import com.cca.moodmeter.topic.model.TopicGroupEntity;

public interface TopicGroupService {

    List<TopicGroupEntity> findSelectedGroups(Long id);

}
