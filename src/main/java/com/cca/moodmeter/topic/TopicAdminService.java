package com.cca.moodmeter.topic;

import java.util.List;

import com.cca.moodmeter.topic.model.TopicAdminEntity;

public interface TopicAdminService {
    List<TopicAdminEntity> findAdmins(Long id);
}
