package com.github.arthurscarpin.email.dto;

import java.util.UUID;

public record EmailDTO(
        UUID id,
        String subject,
        String body
) {
}
