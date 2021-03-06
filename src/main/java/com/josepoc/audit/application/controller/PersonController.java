package com.josepoc.audit.application.controller;

import java.net.URI;
import java.util.UUID;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.josepoc.audit.application.dto.PersonDto;
import com.josepoc.audit.application.mapper.PersonMapper;
import com.josepoc.audit.model.Person;
import com.josepoc.audit.service.PersonService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonMapper personMapper;
    private final PersonService personService;

    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
        Person person = personService.save(personMapper.toEntity(personDto));
        PersonDto dto = personMapper.toDto(person);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                  .path("/{id}")
                                                  .buildAndExpand(person.getId())
                                                  .toUri();
        return ResponseEntity.created(location)
                             .body(dto);
    }

    @GetMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDto> findPersonById(@PathVariable UUID id) {
        PersonDto personDto = personMapper.toDto(
            personService.findById(id)
        );
        return ResponseEntity.ok()
                             .body(personDto);
    }

    @PutMapping(
        path = "/{id}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonDto> updatePerson(@PathVariable UUID id, @RequestBody PersonDto personDto) {
        PersonDto updatedPersonDto = personMapper.toDto(
            personService.update(id, personMapper.toEntity(personDto))
        );
        return ResponseEntity.ok(updatedPersonDto);
    }

    @DeleteMapping(
        path = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> deletePerson(@PathVariable UUID id) {
        personService.delete(id);
        return ResponseEntity.noContent()
                             .build();
    }
}
