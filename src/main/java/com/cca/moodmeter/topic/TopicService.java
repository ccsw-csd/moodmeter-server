package com.cca.moodmeter.topic;

import com.cca.moodmeter.topic.model.*;

import java.util.Date;
import java.util.List;

public interface TopicService {

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicEntity}
     */
    List<TopicEntity> findAll(boolean adminView);

    List<TopicEntity> findByGroups();

    TopicEntity save(TopicDetail data);

    TopicEntity addVisit(Long id);

    TopicEntity get(Long id);

    void vote(Long id, VoteRequestDto data);

    List<TopicSetEntity> findQuestions(Long topicId);

    List<TopicDashboardItem> findAllByUser();

    void launch(Long topicId, Date closeDate);

    TopicDashboardItem getOneItem(Long id);
}
