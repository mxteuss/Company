package com.company.employees.domain.employee.Enum;

import lombok.Getter;

@Getter
public enum Tipo {
    Efetivo("Efetivo"),
    Terceirizado("Terceirizado");
    private final String desc;

    Tipo(String desc){
        this.desc = desc;
    }
    public String getDesc() {
        return desc;
    }

}
