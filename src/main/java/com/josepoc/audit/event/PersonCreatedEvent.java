package com.josepoc.audit.event;

import java.time.Instant;
import com.josepoc.audit.model.Person;

public class PersonCreatedEvent extends DomainEvent<Person> {

    public PersonCreatedEvent(Person person) {
        super(person, Instant.now());
    }
}
