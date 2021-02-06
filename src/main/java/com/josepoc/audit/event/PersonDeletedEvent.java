package com.josepoc.audit.event;

import java.time.Instant;
import com.josepoc.audit.model.Person;

public class PersonDeletedEvent extends DomainEvent<Person> {

    public PersonDeletedEvent(Person person) {
        super(person, Instant.now());
    }
}
