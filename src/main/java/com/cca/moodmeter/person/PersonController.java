package com.cca.moodmeter.person;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(path = "/filter", method = RequestMethod.POST)
    public List<PersonDto> findByFilter(@RequestBody String filter) {

        return this.personService.findByFilter(filter).stream().map(user -> mapper.map(user, PersonDto.class))
                .collect(Collectors.toList());

    }
}
