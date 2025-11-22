package com.company.employees.models.user;

import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class EmailDTO {

    private Long userId;
    private String emailTo;
    private String subject;
    private String text;
}
