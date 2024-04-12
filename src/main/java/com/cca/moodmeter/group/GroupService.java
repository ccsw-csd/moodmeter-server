package com.cca.moodmeter.group;

import java.util.List;

import com.cca.moodmeter.group.model.GroupDto;
import com.cca.moodmeter.group.model.GroupEntity;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link GroupEntity}
     *
     * @return {@link List} de {@link GroupEntity}
     */
    List<GroupEntity> findAll();

    /**
     * Método para crear o actualizar un {@link Group}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, GroupDto dto);

    /**
     * Método para borrar un {@link Group}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

}
