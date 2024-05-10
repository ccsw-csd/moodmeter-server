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

@RequestMapping(value = "/person")
@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    ModelMapper mapper;

    @RequestMapping(path = "/filter/{query}", method = RequestMethod.GET)
    public List<PersonDto> findByFilter(@PathVariable String query) {

        return this.personService.findByFilter(query).stream().map(user -> mapper.map(user, PersonDto.class))
                .collect(Collectors.toList());

    }
}
