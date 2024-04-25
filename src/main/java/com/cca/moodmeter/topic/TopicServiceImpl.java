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
import com.cca.moodmeter.topicoption.TopicOptionRepository;
import com.cca.moodmeter.topicset.TopicSetRepository;

@Service
@Transactional(readOnly = false)
public class TopicServiceImpl implements TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    TopicGroupRepository topicGroupRepository;

    @Autowired
    TopicOptionRepository topicOptionRepository;

    @Autowired
    TopicSetRepository topicSetRepository;

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
                topicGroup.setTopic(topic);

                topicGroup.setGroup(mapper.map(group, GroupEntity.class));

                this.topicGroupRepository.save(topicGroup);
            }
        }

        // Guardar o actualizar las preguntas de la encuesta
        /*
         * if (data.getQuestions() != null) { List<TopicSetDto> questions =
         * data.getQuestions(); for (TopicSetDto question : questions) { TopicSetEntity
         * topicQuestion = new TopicSetEntity();
         * topicQuestion.setOrder(question.getOrder());
         * topicQuestion.setQuestion(question.getQuestion());
         * topicQuestion.setType(question.getType());
         * topicQuestion.setTopic(mapper.map(question.getTopic(), TopicEntity.class));
         * 
         * if (topicQuestion.getId() != null) { topicQuestion.setId(question.getId()); }
         * 
         * this.topicSetRepository.save(topicQuestion); } }
         */
        return this.topicRepository.save(topic);
    }

}
