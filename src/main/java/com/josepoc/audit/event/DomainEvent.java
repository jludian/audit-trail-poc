package com.josepoc.audit.event;

import java.time.Instant;
import com.josepoc.audit.model.DomainEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class DomainEvent<T extends DomainEntity> {

    private T source;
    private Instant timestamp;
}
