package com.cca.moodmeter.topicoption;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topicoption.model.TopicOptionEntity;

public interface TopicOptionRepository extends CrudRepository<TopicOptionEntity, Long> {

    List<TopicOptionEntity> findAllBySetId(Long id);
}
