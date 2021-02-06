package com.josepoc.audit.event.listener;

import com.josepoc.audit.persistence.AuditRecordRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class DomainEventListener {

    protected AuditRecordRepository auditRecordRepository;
}
