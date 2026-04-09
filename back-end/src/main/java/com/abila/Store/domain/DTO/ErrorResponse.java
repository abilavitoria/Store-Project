package com.abila.Store.domain.DTO;

public record ErrorResponse(
        int status,
        String message,
        long timestamp
) {
}
