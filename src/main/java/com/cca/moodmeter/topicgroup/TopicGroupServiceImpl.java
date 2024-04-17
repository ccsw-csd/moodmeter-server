package com.cca.moodmeter.topicgroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.group.model.GroupEntity;

@Service
@Transactional(readOnly = false)
public class TopicGroupServiceImpl implements TopicGroupService {

    @Autowired
    TopicGroupRepository topicGroupRepository;

    @Override
    public List<GroupEntity> findSelectedGroups(Long id) {
        return this.topicGroupRepository.findGroupsByTopicId(id);
    }

}
