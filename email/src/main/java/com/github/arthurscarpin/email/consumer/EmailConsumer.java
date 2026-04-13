package com.github.arthurscarpin.email.consumer;

import com.github.arthurscarpin.email.dto.EmailDTO;
import com.github.arthurscarpin.email.model.Email;
import com.github.arthurscarpin.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailConsumer {

    private final EmailService service;

    @RabbitListener(queues = "${email.queue}")
    public void listenQueue(@Payload EmailDTO emailDTO) {
        var email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        service.send(email);
    }
}