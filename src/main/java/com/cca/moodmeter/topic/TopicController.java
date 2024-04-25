package com.cca.moodmeter.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topic.model.TopicDetail;
import com.cca.moodmeter.topic.model.TopicDto;
import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topicgroup.TopicGroupService;
import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;
import com.cca.moodmeter.topicoption.TopicOptionService;
import com.cca.moodmeter.topicset.TopicSetService;
import com.cca.moodmeter.topicset.model.TopicSetDto;
import com.cca.moodmeter.topicset.model.TopicSetEntity;

@RequestMapping(value = "/topic")
@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicGroupService topicGroupService;

    @Autowired
    TopicSetService topicSetService;

    @Autowired
    TopicOptionService topicOptionService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicDto}
     */

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<TopicDetail> findAll() {

        List<TopicDetail> topicDetailList = new ArrayList<>();
        List<TopicEntity> topics = this.topicService.findAll();

        for (TopicEntity topic : topics) {
            List<TopicGroupEntity> topicGroups = this.topicGroupService.findSelectedGroups(topic.getId());

            List<GroupDto> groups = topicGroups.stream()
                    .map(topicGroup -> mapper.map(topicGroup.getGroup(), GroupDto.class)).collect(Collectors.toList());

            List<TopicSetEntity> topicSets = this.topicSetService.findByTopicId(topic.getId());

            List<TopicSetDto> questions = topicSets.stream().map(e -> mapper.map(e, TopicSetDto.class))
                    .collect(Collectors.toList());

            TopicDetail topicDetail = new TopicDetail();
            topicDetail.setGroups(groups);
            topicDetail.setTopic(mapper.map(topic, TopicDto.class));
            topicDetail.setQuestions(questions);

            topicDetailList.add(topicDetail);
        }

        return topicDetailList;

    }

    /**
     * Método para guardar o actualizar un topic
     *
     */
    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public TopicDto save(@RequestBody TopicDetail data) {

        TopicEntity topic = this.topicService.save(data);

        return mapper.map(topic, TopicDto.class);
    }

}
