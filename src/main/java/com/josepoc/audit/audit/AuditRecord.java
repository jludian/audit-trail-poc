package com.josepoc.audit.audit;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "audit_record")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditRecord {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "audit_action")
    private AuditAction action;

    @Column(name = "date_Created")
    private LocalDateTime dateCreated;

    private UUID resourceId;

    @PrePersist
    private void prePersist() {
        id = UUID.randomUUID();
    }
}

