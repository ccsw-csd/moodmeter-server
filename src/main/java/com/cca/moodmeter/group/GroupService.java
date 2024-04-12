package com.cca.moodmeter.group;

import java.util.List;

import com.cca.moodmeter.group.model.GroupEntity;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link GroupEntity}
     *
     * @return {@link List} de {@link GroupEntity}
     */
    List<GroupEntity> findAll();

}
