package com.cca.moodmeter.group.service;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEditDto;
import com.cca.moodmeter.group.model.GroupEntity;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link GroupEntity}
     *
     * @return {@link List} de {@link GroupEntity}
     */
    List<GroupEntity> findAll();

    /**
     * Método para recuperar una lista de grupos. Si el usuario es administrador,
     * llama a findAll. Si no, devuelve la lista de grupos en los que está adscrito
     * 
     * @param isAdmin boolean para saber si el usuario es administador
     * @return devuelve la lista de GroupEntity
     */
    List<GroupEntity> findGroups(boolean isAdmin);

    /**
     * Método para borrar un {@link GroupEditDto}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Método para devolver un GroupEditDto para guardar
     * 
     * @param id
     * @return
     */
    GroupEditDto findGroupEdit(Long id);

    /**
     * Método para guardar un Group y su personal y admins. Si es nuevo, lo crea. Si
     * no, lo edita destruyendo las referencias a personal y administradores de la
     * base de datos y lo crea de nuevo con las listas dentro de groupEditDto
     * 
     * @param id
     * @param dto datos de la entidad
     */
    void save(Long id, GroupEditDto groupEditDto);

    /**
     * Encuentra un {@link GroupEntity} por su id y lo devuelve
     * 
     * @param id
     * @return GroupEntity
     */
    GroupEntity findGroupById(Long id);

}
