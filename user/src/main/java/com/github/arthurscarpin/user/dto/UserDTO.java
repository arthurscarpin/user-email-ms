package com.github.arthurscarpin.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank String name,
        @Email @NotBlank String email
) {
}
