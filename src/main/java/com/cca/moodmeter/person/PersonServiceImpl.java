package com.cca.moodmeter.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cca.moodmeter.person.model.PersonEntity;

@Service
@Transactional(readOnly = false)
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    /**
     * {@inheritDoc}
     */
    public PersonEntity getPersonByUsername(String username) {
        return personRepository.getByUsernameLike(username);
    }

    @Override
    public List<PersonEntity> findByFilter(String filter) {
        return this.personRepository.findUsersLikeFilter(filter, PageRequest.of(0, 15));
    }

}
