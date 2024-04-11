package com.cca.moodmeter.group;

import java.util.List;

import com.cca.moodmeter.group.model.Group;
import com.cca.moodmeter.group.model.GroupDto;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link Group}
     *
     * @return {@link List} de {@link Group}
     */
    List<Group> findAll();

    /**
     * Método para crear o actualizar un {@link Group}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, GroupDto dto);

}
