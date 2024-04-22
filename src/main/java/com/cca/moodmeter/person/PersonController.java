package com.cca.moodmeter.person;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cca.moodmeter.person.model.PersonDto;
import com.cca.moodmeter.person.model.PersonEntity;

/**
 * @author mguaitav
 */
@RequestMapping(value = "/person")
@RestController
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(path = "/{matchUsernameNameLastname}", method = RequestMethod.GET)
    public List<PersonDto> findByFilter(
            @PathVariable(name = "matchUsernameNameLastname") String matchUsernameNameLastname) {

        List<PersonEntity> personEntities = this.personService.findByFilter(matchUsernameNameLastname);

        return personEntities.stream().map(e -> mapper.map(e, PersonDto.class)).collect(Collectors.toList());
    }
}
