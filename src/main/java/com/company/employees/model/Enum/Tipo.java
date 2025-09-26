package com.company.employees.model.Enum;

import lombok.Getter;

@Getter
public enum Tipo {
    EFETIVO("Efetivo"),
    TERCEIRIZADO("Terceirizado");

    private final String desc;

    Tipo(String desc){
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

}
