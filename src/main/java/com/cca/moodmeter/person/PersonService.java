package com.cca.moodmeter.person;

import java.util.List;

import com.cca.moodmeter.person.model.PersonEntity;

public interface PersonService {

    /**
     * Método para recuperar los usuarios cuyo username, name o lastname sean
     * iguales al texto del parametro
     * 
     * @param matchUsernameNameLastname
     */
    List<PersonEntity> findByFilter(String matchUsernameNameLastname);

}
