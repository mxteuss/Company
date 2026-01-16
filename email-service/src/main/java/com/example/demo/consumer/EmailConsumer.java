package com.example.demo.consumer;

import com.example.demo.dto.EmailDTO;
import com.example.demo.model.EmailModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Component
public class    EmailConsumer {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void listenEmail(@Payload EmailDTO emailDTO) {
        System.out.println(">>> ENTROU NO LISTENER");
        var EmailModel = new EmailModel();

        try {
            EmailModel.setUserId(emailDTO.userId());
            EmailModel.setEmailTo(emailDTO.emailTo());
            EmailModel.setSubject(emailDTO.subject());
            EmailModel.setText(emailDTO.text());
            EmailModel.setSendDateEmail(LocalDateTime.now());
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
        rabbitTemplate.convertAndSend("", "email-queue", EmailModel);
    }
}
