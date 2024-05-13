package com.cca.moodmeter.group.service;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.person.model.PersonDto;
import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupAdminService {

    /**
     * Devuelve una lista de todos los {@link PersonEntity} de los administradores
     * de un grupo
     * 
     * @param groupId
     * @return {@link List} of {@link PersonEntity}
     */
    public List<PersonEntity> findAllAdminsInGroup(Long groupId);

    /**
     * Elimina todos los administradores del grupo cuyo id se pasa como parámetro
     * 
     * @param group
     */
    public void deleteAllAdminsInGroup(GroupEntity group);

    /**
     * Añade las {@ PersonEntity} que haya en la lista como administradores del
     * grupo cuyo id se pasa como parámetro
     * 
     * @param id
     * @param admins
     */
    public void addAllAdminsInGroup(Long id, List<PersonDto> admins);

}
