package com.josepoc.audit.event.listener;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import com.josepoc.audit.audit.AuditAction;
import com.josepoc.audit.audit.AuditRecord;
import com.josepoc.audit.event.PersonCreatedEvent;
import com.josepoc.audit.persistence.AuditRecordRepository;

@Component
public class PersonCreatedListener extends DomainEventListener {

    public PersonCreatedListener(AuditRecordRepository auditRecordRepository) {
        super(auditRecordRepository);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(
        classes = PersonCreatedEvent.class,
        phase = TransactionPhase.AFTER_COMMIT
    )
    public void handlePersonCreated(PersonCreatedEvent event) {
        var auditRecord = AuditRecord.builder()
                                     .action(AuditAction.PERSON_CREATED)
                                     .resourceId(event.getSource().getId())
                                     .dateCreated(LocalDateTime.ofInstant(event.getTimestamp(), ZoneOffset.UTC))
                                     .build();
        auditRecordRepository.save(auditRecord);
    }
}
