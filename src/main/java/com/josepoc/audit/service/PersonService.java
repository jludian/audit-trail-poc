package com.josepoc.audit.service;

import java.util.UUID;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.josepoc.audit.event.PersonCreatedEvent;
import com.josepoc.audit.event.PersonDeletedEvent;
import com.josepoc.audit.exception.ResourceNotFoundException;
import com.josepoc.audit.model.Person;
import com.josepoc.audit.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Person save(Person person) {
        Person savedPerson = personRepository.save(person);
        eventPublisher.publishEvent(new PersonCreatedEvent(person));
        return savedPerson;
    }

    public Person findById(UUID id) {
        return personRepository.findById(id)
                               .orElseThrow(() -> new ResourceNotFoundException("unable to find Person with id " + id));
    }

    @Transactional
    public Person update(UUID id, Person personUpdate) {
        Person person = findById(id);
        person.setName(personUpdate.getName());
        person.setLastName(personUpdate.getLastName());
        return person;
    }

    @Transactional
    public void delete(UUID id) {
        Person person = findById(id);
        personRepository.delete(person);
        eventPublisher.publishEvent(new PersonDeletedEvent(person));
    }
}
