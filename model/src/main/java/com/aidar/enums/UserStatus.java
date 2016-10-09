package com.aidar.enums;

/**
 * Created by paradise on 13.04.16.
 */
public enum UserStatus {

    ACTIVE("active"),
    BANNED("banned");

    private String representation;

    UserStatus(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
