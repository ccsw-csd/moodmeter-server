package com.cca.moodmeter.group.service;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEntity;
import com.cca.moodmeter.person.model.PersonDto;
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
     * @param group
     */
    public void deleteAllMembersInGroup(GroupEntity group);

    /**
     * Añade las {@ PersonEntity} que haya en la lista como personal del grupo cuyo
     * id se pasa como parámetro
     * 
     * @param id
     * @param members
     */
    public void addAllMembersInGroup(Long id, List<PersonDto> members);

}
