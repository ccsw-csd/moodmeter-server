package com.cca.moodmeter.topicoption;

import java.util.List;

import com.cca.moodmeter.topicoption.model.TopicOptionEntity;

public interface TopicOptionService {

    List<TopicOptionEntity> findBySetId(Long id);
}
