package com.cca.moodmeter.topicgroup;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEntity;

public interface TopicGroupService {

    List<GroupEntity> findSelectedGroups(Long id);

}
