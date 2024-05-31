package com.cca.moodmeter.topic;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topic.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    private TopicVotedByService topicVotedByService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicDto}
     */

    @GetMapping(path = "/")
    public List<TopicItemListDto> findAll(@RequestParam(defaultValue = "false", value = "adminView") boolean adminView) {

        List<TopicEntity> topics = this.topicService.findAll(adminView);

        return topics.stream().map(topic -> mapper.map(topic, TopicItemListDto.class)).collect(Collectors.toList());
    }

    @GetMapping(path = "/by-user")
    public List<TopicDashboardItem> findAllByUser() {
        return this.topicService.findAllByUser();

    }

    @GetMapping(path = "/{id}/get-item")
    public TopicDashboardItem getOneItem(@PathVariable("id") Long id) {
        return this.topicService.getOneItem(id);

    }

    @GetMapping(path = "/{id}")
    public TopicDetail get(@PathVariable("id") Long id) {

        TopicEntity topic = this.topicService.get(id);

        List<TopicGroupEntity> topicGroups = this.topicGroupService.findSelectedGroups(topic.getId());
        List<GroupDto> groups = topicGroups.stream().map(topicGroup -> mapper.map(topicGroup.getGroup(), GroupDto.class)).collect(Collectors.toList());

        List<TopicAdminDto> admins = this.topicAdminService.findAdmins(topic.getId()).stream().map(admin -> mapper.map(admin, TopicAdminDto.class)).collect(Collectors.toList());
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setGroups(groups);
        topicDetail.setTopic(mapper.map(topic, TopicDto.class));
        topicDetail.setAdmins(admins);
        topicDetail.setVotes(this.topicVotedByService.getVotes(topic));
        TopicVotedByEntity topicVoted = this.topicVotedByService.findVote(topic.getId());
        topicDetail.setUserVotingDate(topicVoted.getVotingDate());

        return topicDetail;
    }

    /**
     * Método para guardar o actualizar un topic
     *
     */
    @PutMapping(path = "/")
    public TopicDto save(@RequestBody TopicDetail data) {

        TopicEntity topic = this.topicService.save(data);

        return mapper.map(topic, TopicDto.class);
    }

    /**
     * Método para aumentar el número de visitas de la encuesta
     *
     */
    @PutMapping(path = "/{id}/visit")
    public TopicDto save(@PathVariable Long id) {

        TopicEntity topic = this.topicService.addVisit(id);

        return mapper.map(topic, TopicDto.class);
    }

    /**
     * Método para guardar votos de una encuesta
     *
     */
    @PutMapping(path = "/{id}/vote")
    public void vote(@PathVariable Long id, @RequestBody VoteRequestDto data) {

        this.topicService.vote(id, data);

    }

    /**
     * Método para recuperar todas las preguntas de una encuesta
     *
     * @return {@link List} de {@link TopicSetDto}
     */

    @GetMapping(path = "/{topicId}/questions")
    public List<TopicSetSimpleDto> findQuestions(@PathVariable("topicId") Long topicId) {

        List<TopicSetEntity> questions = this.topicService.findQuestions(topicId);

        return questions.stream().map(question -> mapper.map(question, TopicSetSimpleDto.class)).collect(Collectors.toList());

    }

    @PostMapping(path = "/{id}/launch")
    public void launch(@PathVariable Long id, @RequestBody TopicLaunchRequestDto data) {

        this.topicService.launch(id, data.getDate());

    }

}
