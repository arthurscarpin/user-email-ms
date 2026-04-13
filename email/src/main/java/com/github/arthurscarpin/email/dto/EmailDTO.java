package com.github.arthurscarpin.email.dto;

import java.util.UUID;

public record EmailDTO(
        UUID userId,
        String emailTo,
        String subject,
        String body
) {
}
