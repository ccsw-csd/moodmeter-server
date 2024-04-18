package com.cca.moodmeter.topicgroup;

import java.util.List;

import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;

public interface TopicGroupService {

    List<TopicGroupEntity> findSelectedGroups(Long id);

}
