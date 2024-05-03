package com.cca.moodmeter.groupStaff;

import java.util.List;

import com.cca.moodmeter.person.model.PersonEntity;

public interface GroupStaffService {

    /**
     * Devuelve una lista de todos los {@link PersonEntity} del personal de un grupo
     * 
     * @param groupId
     * @return {@link List} of {@link PersonEntity}
     */
    public List<PersonEntity> findAllMembersInGroup(Long groupId);

    /**
     * Elimina todo el personal del grupo cuyo id se pasa como parámetro
     * 
     * @param id
     */
    public void deleteAllMembersInGroup(Long id);

    /**
     * Añade las {@ PersonEntity} que haya en la lista como personal del grupo cuyo
     * id se pasa como parámetro
     * 
     * @param id
     * @param members
     */
    public void addAllMembersInGroup(Long id, List<PersonEntity> members);

}
