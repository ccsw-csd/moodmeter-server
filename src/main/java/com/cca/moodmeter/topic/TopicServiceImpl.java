package com.cca.moodmeter.topic;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.config.security.UserUtils;
import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.topic.model.TopicDetail;
import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topicgroup.TopicGroupRepository;
import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;

@Service
@Transactional(readOnly = false)
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicGroupRepository topicGroupRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public List<TopicEntity> findAll() {
        return (List<TopicEntity>) this.topicRepository.findAll();
    }

    @Override
    public TopicEntity save(TopicDetail data) {
        TopicEntity topic = new TopicEntity();
        topic.setTitle(data.getTopic().getTitle());
        topic.setStatus(data.getTopic().getStatus());
        topic.setUpdateDate(data.getTopic().getUpdateDate());
        topic.setUpdateUsername(UserUtils.getUserDetails().getUsername());
        topic.setCreationDate(data.getTopic().getCreationDate());
        if (data.getTopic().getId() != null) {
            topic.setId(data.getTopic().getId());
            topic.setCreationUsername(data.getTopic().getCreationUsername());
            topic.setVisits(data.getTopic().getVisits());
        } else {
            topic.setCreationUsername(UserUtils.getUserDetails().getUsername());
            long visits = 0;
            topic.setVisits(visits);
        }

        this.topicGroupRepository.deleteByTopicId(data.getTopic().getId());

        if (data.getGroups() != null) {
            List<GroupDto> groups = data.getGroups();
            for (GroupDto group : groups) {
                TopicGroupEntity topicGroup = new TopicGroupEntity();
                topicGroup.setTopic(mapper.map(data.getTopic(), TopicEntity.class));

                topicGroup.setGroup(mapper.map(group, GroupEntity.class));

                this.topicGroupRepository.save(topicGroup);
            }
        }

        return this.topicRepository.save(topic);
    }

}
