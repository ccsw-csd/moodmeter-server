package com.cca.moodmeter.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cca.moodmeter.person.model.PersonEntity;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<PersonEntity> findByFilter(String matchUsernameNameLastname) {

        return (List<PersonEntity>) this.personRepository.findUsersLikeFilter(matchUsernameNameLastname);
    }

    /**
     * {@inheritDoc}
     */
    public PersonEntity getPersonByName(String username) {
        return personRepository.getByUsernameLike(username);
    }

}
