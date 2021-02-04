package com.josepoc.audit.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class PersonDto {

    private String name;
    private String lastName;
}
