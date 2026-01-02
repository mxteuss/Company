package com.company.employees.producer;

import com.company.employees.models.employee.Employee;
import com.company.employees.models.user.EmailDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProducer {

    private final RabbitTemplate rabbitTemplate;

    public EmployeeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void sendEmail(Employee employee) {
        var emailDto = new EmailDTO();
        emailDto.setUserId(employee.getId());
        emailDto.setEmailTo(employee.getEmail());
        emailDto.setSubject("Company");
        emailDto.setText("Parábens por se juntar ao time!");

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
