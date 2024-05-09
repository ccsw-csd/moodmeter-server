package com.cca.moodmeter.person;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cca.moodmeter.person.model.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    PersonEntity findByUsernameAndActiveTrue(String username);

    @Query("select u from PersonEntity u where concat(name, ' ', lastname, ' ', username) LIKE %:filter% order by name, lastname asc")
    List<PersonEntity> findUsersLikeFilter(String filter, Pageable pageable);
}
