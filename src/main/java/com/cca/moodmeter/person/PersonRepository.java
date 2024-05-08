package com.cca.moodmeter.person;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.person.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    @Query("SELECT p FROM PersonEntity p WHERE p.username = :username AND p.active = 1")
    PersonEntity findByUsernameAndActive(String username);
}
