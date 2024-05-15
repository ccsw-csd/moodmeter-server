package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.person.model.PersonEntity;
import com.cca.moodmeter.topic.model.TopicAdminEntity;

public interface TopicAdminRepository extends CrudRepository<TopicAdminEntity, Long> {

    List<TopicAdminEntity> findAllByTopicId(Long id);

    void deleteByTopicId(Long id);

    List<TopicAdminEntity> findAllByPerson(PersonEntity person);

    boolean existsByPersonIdAndTopicId(Long personId, Long id);

}
