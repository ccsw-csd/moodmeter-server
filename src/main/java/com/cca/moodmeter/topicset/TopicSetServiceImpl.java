package com.cca.moodmeter.topicset;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.topicset.model.TopicSetEntity;

@Service
@Transactional(readOnly = false)
public class TopicSetServiceImpl implements TopicSetService {

    @Autowired
    TopicSetRepository topicSetRepository;

    @Override
    public List<TopicSetEntity> findByTopicId(Long id) {
        return this.topicSetRepository.findAllByTopicId(id);
    }

}
