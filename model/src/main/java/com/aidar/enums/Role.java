package com.aidar.enums;

/**
 * Created by paradise on 08.04.16.
 */
public enum Role {

    ROLE_USER("user"),
    ROLE_ADMIN("super user");

    private String representation;

    Role(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

}
