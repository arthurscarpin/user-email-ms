package com.github.arthurscarpin.user.exception;

public record ErrorResponse(
        Integer statusCode,
        String message
) {
}
