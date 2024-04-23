package com.cca.moodmeter.topicoption;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.topicoption.model.TopicOptionEntity;

@Service
@Transactional(readOnly = false)
public class TopicOptionServiceImpl implements TopicOptionService {

    @Autowired
    TopicOptionRepository topicOptionRepository;

    @Override
    public List<TopicOptionEntity> findBySetId(Long id) {
        return this.topicOptionRepository.findAllBySetId(id);
    }

}
