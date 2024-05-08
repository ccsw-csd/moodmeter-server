package com.cca.moodmeter.person;

import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.person.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByUsernameAndActiveTrue(String username);
}
