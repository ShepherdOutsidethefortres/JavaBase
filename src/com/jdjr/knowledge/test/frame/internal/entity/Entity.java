package com.jdjr.knowledge.test.frame.internal.entity;

public interface Entity {
    public static enum EntityType {
        HTTP,
        HTTPS,
        JSF;

        private EntityType() {
        }
    }
}
