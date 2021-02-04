package com.josepoc.audit.application.mapper;

import org.springframework.stereotype.Component;
import com.josepoc.audit.application.dto.PersonDto;
import com.josepoc.audit.model.Person;

@Component
public class PersonMapper {

    public Person toEntity(PersonDto personDto){
        return Person.builder()
                     .name(personDto.getName())
                     .lastName(personDto.getLastName())
                     .build();
    }

    public PersonDto toDto(Person person){
        return PersonDto.builder()
                        .name(person.getName())
                        .lastName(person.getLastName())
                        .build();
    }
}
