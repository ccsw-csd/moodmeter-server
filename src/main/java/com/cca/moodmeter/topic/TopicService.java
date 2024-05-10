package com.cca.moodmeter.topic;

import java.util.List;

import com.cca.moodmeter.topic.model.TopicDetail;
import com.cca.moodmeter.topic.model.TopicDto;
import com.cca.moodmeter.topic.model.TopicEntity;

public interface TopicService {

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicEntity}
     */
    List<TopicEntity> findAll(boolean adminView);

    TopicEntity save(TopicDetail data);

    TopicEntity addVisit(Long id);

    void saveVote(TopicDto data);
}
