package com.github.arthurscarpin.user.producer;

import com.github.arthurscarpin.user.dto.EmailDTO;
import com.github.arthurscarpin.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserProducer {

    private final RabbitTemplate template;

    @Value("${rabbitmq.routing-key}")
    private String routingKey;

    public void publishEvent(User user) {
        var emailDTO = new EmailDTO();
        emailDTO.setUserId(user.getId());
        emailDTO.setEmailTo(user.getEmail());
        emailDTO.setSubject("Welcome to our system!");
        emailDTO.setBody("Hello " + user.getName() + ",\n\nThank you for registering.");

        template.convertAndSend("", routingKey, emailDTO);
    }
}
