package com.cca.moodmeter.topic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topic.model.TopicAdminDto;
import com.cca.moodmeter.topic.model.TopicDetail;
import com.cca.moodmeter.topic.model.TopicDto;
import com.cca.moodmeter.topic.model.TopicEntity;
import com.cca.moodmeter.topic.model.TopicGroupEntity;

@RequestMapping(value = "/topic")
@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    private TopicGroupService topicGroupService;

    @Autowired
    private TopicAdminService topicAdminService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicDto}
     */

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<TopicDetail> findAll(@RequestParam(defaultValue = "false", value = "adminView") boolean adminView) {

        List<TopicDetail> topicDetailList = new ArrayList<>();
        List<TopicEntity> topics = this.topicService.findAll(adminView);

        for (TopicEntity topic : topics) {
            List<TopicGroupEntity> topicGroups = this.topicGroupService.findSelectedGroups(topic.getId());

            List<GroupDto> groups = topicGroups.stream()
                    .map(topicGroup -> mapper.map(topicGroup.getGroup(), GroupDto.class)).collect(Collectors.toList());

            List<TopicAdminDto> admins = this.topicAdminService.findAdmins(topic.getId()).stream()
                    .map(admin -> mapper.map(admin, TopicAdminDto.class)).collect(Collectors.toList());
            TopicDetail topicDetail = new TopicDetail();
            topicDetail.setGroups(groups);
            topicDetail.setTopic(mapper.map(topic, TopicDto.class));
            topicDetail.setAdmins(admins);

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

    /**
     * Método para aumentar el número de visitas de la encuesta
     *
     */
    @RequestMapping(path = "/{id}/visit", method = RequestMethod.PUT)
    public TopicDto save(@PathVariable Long id) {

        TopicEntity topic = this.topicService.addVisit(id);

        return mapper.map(topic, TopicDto.class);
    }

    /**
     * Método para guardar votos de una encuesta
     *
     */
    @RequestMapping(path = "/vote", method = RequestMethod.PUT)
    public void vote(@RequestBody TopicDto data) {

        this.topicService.saveVote(data);

    }

}
