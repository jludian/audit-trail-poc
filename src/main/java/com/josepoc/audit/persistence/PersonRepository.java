package com.josepoc.audit.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.josepoc.audit.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

}
