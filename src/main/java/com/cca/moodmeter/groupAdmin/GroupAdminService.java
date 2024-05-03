package com.cca.moodmeter.groupAdmin;

import java.util.List;

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
     * @param id
     */
    public void deleteAllAdminsInGroup(Long id);

    /**
     * Añade las {@ PersonEntity} que haya en la lista como administradores del
     * grupo cuyo id se pasa como parámetro
     * 
     * @param id
     * @param admins
     */
    public void addAllAdminsInGroup(Long id, List<PersonEntity> admins);

}
