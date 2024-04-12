package com.cca.moodmeter.group;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEntity;

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
    ModelMapper mapper;

    /**
     * Método para recuperar una lista de {@link Game}
     *
     * @param name              nombre del grupo
     * @param creation_date     fecha de la creación del grupo
     * @param creation_username nombre de creador del grupo
     * @param update_date       fecha de la edición del grupo
     * @param update_username   nombre de editor del grupo
     * @return {@link List} de {@link GroupDto}
     */
    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<GroupDto> findAll() {
        List<GroupEntity> groupEntities = this.groupService.findAll();

        return groupEntities.stream().map(e -> mapper.map(e, GroupDto.class)).collect(Collectors.toList());

    }

    /**
     * Método para crear o actualizar un grupo
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    @RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
    public void save(@PathVariable(name = "id", required = false) Long id, @RequestBody GroupDto dto) {

        this.groupService.save(id, dto);
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

}
