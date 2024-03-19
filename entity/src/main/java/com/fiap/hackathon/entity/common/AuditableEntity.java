package com.fiap.hackathon.entity.common;

import java.time.LocalDateTime;

public abstract class AuditableEntity {
    protected LocalDateTime createdAt;
    protected LocalDateTime modifiedAt;
    protected LocalDateTime deletedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}