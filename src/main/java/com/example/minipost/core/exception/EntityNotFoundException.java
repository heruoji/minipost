package com.example.minipost.core.exception;

public class EntityNotFoundException extends RuntimeException {
    private final String entityName;

    public EntityNotFoundException(String entityName, String message) {
        super(message);
        this.entityName = entityName;
    }

    public String getEntityName() {
        return entityName;
    }
}
