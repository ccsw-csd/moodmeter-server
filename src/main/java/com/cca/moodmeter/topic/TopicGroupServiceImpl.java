package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.topic.model.TopicGroupEntity;

@Service
@Transactional(readOnly = false)
public class TopicGroupServiceImpl implements TopicGroupService {

    @Autowired
    TopicGroupRepository topicGroupRepository;

    @Override
    public List<TopicGroupEntity> findSelectedGroups(Long id) {
        return this.topicGroupRepository.findGroupsByTopicId(id);
    }

}
