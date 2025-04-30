package com.gamehaven.steam.exceptions;

import java.time.Instant;

public record ErrorResponse(
        Instant timestamp,
        int status,
        String error,
        String message,
        String path   // filled automatically in the handler
) { }
