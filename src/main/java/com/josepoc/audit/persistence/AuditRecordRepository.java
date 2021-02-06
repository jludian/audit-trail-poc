package com.josepoc.audit.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.josepoc.audit.audit.AuditRecord;

public interface AuditRecordRepository extends JpaRepository<AuditRecord, UUID> {
}
