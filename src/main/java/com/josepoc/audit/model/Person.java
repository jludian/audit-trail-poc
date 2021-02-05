package com.josepoc.audit.model;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person implements BaseEntity {

    @Id
    private UUID id;

    private String name;
    private String lastName;

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }
}
