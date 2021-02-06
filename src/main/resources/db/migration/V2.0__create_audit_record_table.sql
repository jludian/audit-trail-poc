CREATE TABLE audit_record (
    id BINARY(16) PRIMARY KEY,
    audit_action VARCHAR(255),
    date_Created DATETIME(6) NOT NULL,
    resource_id BINARY(16) NOT NULL
);
