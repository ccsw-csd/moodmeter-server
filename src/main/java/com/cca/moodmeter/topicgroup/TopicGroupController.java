package com.cca.moodmeter.topicgroup;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.topicgroup.model.TopicGroupEntity;

@RequestMapping(value = "/topicgroup")
@RestController
public class TopicGroupController {
    @Autowired
    private TopicGroupService topicGroupService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para recuperar todos los grupos asignados a una encuesta
     *
     * @return {@link List} de {@link Group}
     */

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GroupDto> findSelectedGroups(@RequestParam Long id) {

        List<TopicGroupEntity> topicGroups = this.topicGroupService.findSelectedGroups(id);

        List<GroupDto> groups = topicGroups.stream()
                .map(topicGroup -> mapper.map(topicGroup.getGroup(), GroupDto.class)).collect(Collectors.toList());
        return groups;

    }

}