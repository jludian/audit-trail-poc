package com.josepoc.audit.model;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person implements BaseEntity {

    private UUID id;
    private String name;
    private String lastName;
}
