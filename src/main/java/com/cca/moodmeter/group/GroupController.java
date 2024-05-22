package com.cca.moodmeter.group;

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
import com.cca.moodmeter.group.model.GroupEditDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.group.service.GroupService;
import com.cca.moodmeter.person.PersonService;

/**
 * @author mguaitav
 *
 */
@RequestMapping(value = "/group")
@RestController
public class GroupController {

    @Autowired
    GroupService groupService;

    @Autowired
    PersonService personService;

    @Autowired
    ModelMapper mapper;

    /**
     * Método para encontrar los grupos. Si el usuario es administrador, devuelve
     * todos. Si no lo es, devuelve solo a los que este está adscrito
     * 
     * @param isAdmin si el usuario es administrador
     * @return List de GroupDto
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GroupDto> findGroups(@RequestParam(value = "isAdmin", required = false) boolean isAdmin) {
        List<GroupEntity> groupEntities = this.groupService.findGroups(isAdmin);
        return groupEntities.stream().map(e -> mapper.map(e, GroupDto.class)).collect(Collectors.toList());
    }

    /**
     * Método para borrar un grupo
     *
     * @param id PK de la entidad
     */
    @RequestMapping(path = { "/{id}" }, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) throws Exception {

        this.groupService.delete(id);
    }

    /**
     * Método para encontrar un GroupDto, con su Group, sus miembros y sus admin
     * 
     * @param id id del Group
     * @return GroupEditDto
     */
    @RequestMapping(path = "/{groupId}", method = RequestMethod.GET)
    public GroupEditDto findGroupEditDto(@PathVariable(name = "groupId") Long id) {
        GroupEditDto group = this.groupService.findGroupEdit(id);

        return group;
    }

    /**
     * Método para guardar o editar un grupo, con sus admins y su personal
     * 
     * @param id           id del Group
     * @param GroupEditDto
     */
    @RequestMapping(path = { "", "/{groupId}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "groupId", required = false) Long id,
            @RequestBody GroupEditDto groupEditDto) {
        this.groupService.save(id, groupEditDto);
    }

}
