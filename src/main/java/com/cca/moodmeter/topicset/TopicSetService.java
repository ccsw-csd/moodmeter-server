package com.cca.moodmeter.topicset;

import java.util.List;

import com.cca.moodmeter.topicset.model.TopicSetEntity;

public interface TopicSetService {

    List<TopicSetEntity> findByTopicId(Long id);

}
