package com.cca.moodmeter.group;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEditDto;
import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link GroupEntity}
     *
     * @return {@link List} de {@link GroupEntity}
     */
    List<GroupEntity> findAll();

    /**
     * Método para borrar un {@link GroupEditDto}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;

    /**
     * Método para buscar {@link PersonEntity} con filtro
     * 
     * @param nameLastnameUsername string para filtrado
     */
    List<PersonEntity> searchByNameLastnameUsername(String nameLastnameUsername) throws Exception;

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

}
