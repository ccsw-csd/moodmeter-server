package com.cca.moodmeter.topic;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.topic.model.TopicDetail;
import com.cca.moodmeter.topic.model.TopicDto;
import com.cca.moodmeter.topic.model.TopicEntity;

@RequestMapping(value = "/topic")
@RestController
public class TopicController {
    @Autowired
    private TopicService topicService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los topic
     *
     * @return {@link List} de {@link TopicDto}
     */

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public List<TopicDto> findAll() {

        List<TopicEntity> topics = this.topicService.findAll();

        return topics.stream().map(e -> mapper.map(e, TopicDto.class)).collect(Collectors.toList());

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
