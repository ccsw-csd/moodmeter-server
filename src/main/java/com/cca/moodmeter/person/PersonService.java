package com.cca.moodmeter.person;

import java.util.List;

import com.cca.moodmeter.person.model.PersonEntity;

public interface PersonService {

    List<PersonEntity> findByFilter(String filter);
}
