package com.josepoc.audit.persistence;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Repository;
import com.josepoc.audit.model.Person;

@Repository
public class PersonRepository {

    private final Set<Person> persons = new HashSet<>();

    public Person save(Person person) {
        if (person.getId() == null) {
            person.setId(UUID.randomUUID());
        }
        else {
            update(person);
        }
        return person;
    }

    public Optional<Person> findById(UUID id) {
        return persons.stream()
                      .filter(person -> person.getId().equals(id))
                      .findFirst();
    }

    public void delete(Person person) {
        persons.remove(person);
    }

    private void update(Person person) {
        findById(person.getId()).ifPresent(this::delete);
        persons.add(person);
    }
}
