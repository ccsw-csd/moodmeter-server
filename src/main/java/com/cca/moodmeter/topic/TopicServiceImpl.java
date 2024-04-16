package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.topic.model.TopicDto;
import com.cca.moodmeter.topic.model.TopicEntity;

@Service
@Transactional(readOnly = false)
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public List<TopicEntity> findAll() {
        return (List<TopicEntity>) this.topicRepository.findAll();
    }

    @Override
    public TopicEntity save(TopicDto data) {
        TopicEntity topic = new TopicEntity();
        topic.setTitle(data.getTitle());
        topic.setStatus(data.getStatus());
        topic.setUpdateDate(data.getUpdateDate());
        topic.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        topic.setCreationDate(data.getCreationDate());
        if (data.getId() != null) {
            topic.setId(data.getId());
            topic.setCreationUsername(data.getCreationUsername());
            topic.setVisits(data.getVisits());
        } else {
            topic.setCreationUsername(UserUtils.getUserDetails().getUsername());
            long visits = 0;
            topic.setVisits(visits);
        }

        return this.topicRepository.save(topic);
    }

}
