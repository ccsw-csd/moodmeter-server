package com.cca.moodmeter.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.topic.model.TopicAdminEntity;

@Service
@Transactional(readOnly = false)
public class TopicAdminServiceImpl implements TopicAdminService {

    @Autowired
    TopicAdminRepository topicAdminRepository;

    @Override
    public List<TopicAdminEntity> findAdmins(Long id) {
        return this.topicAdminRepository.findAllByTopicId(id);
    }

}
