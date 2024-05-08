package com.cca.moodmeter.topic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.topic.model.TopicVotedByEntity;

public interface TopicVotedByRepository extends CrudRepository<TopicVotedByEntity, Long> {

    @Query("SELECT COUNT(tv) > 0 FROM TopicVotedByEntity tv WHERE tv.topic.id = :id AND tv.person.username = :username")
    boolean existsByTopicIdAndUsername(Long id, String username);

}
