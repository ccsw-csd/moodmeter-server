package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.person.PersonRepository;
import com.cca.moodmeter.person.model.PersonEntity;
import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topic.model.TopicVotedByEntity;

@Service
@Transactional(readOnly = false)
public class TopicVotedByServiceImpl implements TopicVotedByService {

    @Autowired
    TopicVotedByRepository topicVotedByRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public Integer getVotes(TopicEntity topic) {
        Integer votes = 0;

        List<TopicVotedByEntity> votesList = this.topicVotedByRepository.findAllByTopicId(topic.getId());
        votes = votesList.size();
        return votes;
    }

    @Override
    public TopicVotedByEntity findVote(Long topicId) {
        String user = UserUtils.getUserDetails().getUsername();
        PersonEntity person = personRepository.findByUsernameAndActiveTrue(user);
        Long personId = person.getId();
        TopicVotedByEntity vote = this.topicVotedByRepository.findByTopicIdAndPersonId(topicId, personId);
        if (vote == null) {
            vote = new TopicVotedByEntity();
        }
        return vote;
    }

}
