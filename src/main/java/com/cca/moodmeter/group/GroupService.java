package com.cca.moodmeter.group;

import java.util.List;

import com.cca.moodmeter.group.model.Group;

public interface GroupService {

    /**
     * Método para recuperar todos los {@link Group}
     *
     * @return {@link List} de {@link Group}
     */
    List<Group> findAll();

}
