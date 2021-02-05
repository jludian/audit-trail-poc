package com.josepoc.audit.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.josepoc.audit.exception.ResourceNotFoundException;
import com.josepoc.audit.model.Person;
import com.josepoc.audit.persistence.PersonRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public Person findById(UUID id) {
        return personRepository.findById(id)
                               .orElseThrow(()->new ResourceNotFoundException("unable to find Person with id " + id));
    }

    public Person update(UUID id, Person personUpdate) {
        Person person = findById(id);
        person.setName(personUpdate.getName());
        person.setLastName(personUpdate.getLastName());
        return personRepository.save(person);
    }

    public void delete(UUID id){
        Person person = findById(id);
        personRepository.delete(person);
    }
}
