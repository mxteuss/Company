package com.company.employees.domain.model.Enum;

import lombok.Getter;

@Getter
public enum Roles {
    ADMIN("Admin"),
    USER("User");

    private String desc;

    Roles(String desc) {
        this.desc = desc;
    }

    Roles() {
    }
}
