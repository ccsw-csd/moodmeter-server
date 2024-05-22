package com.cca.moodmeter.topic;

import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topic.model.TopicVotedByEntity;

public interface TopicVotedByService {

    Integer getVotes(TopicEntity topic);

    TopicVotedByEntity findVote(Long topicId);
}
