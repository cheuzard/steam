// src/main/java/com/gamehaven/steam/controller/GlobalExceptionHandler.java
package com.gamehaven.steam.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /* ---------- helper ---------- */
    private ResponseEntity<ErrorResponse> build(HttpStatus status,
                                                String error, String msg,
                                                HttpServletRequest request) {
        return ResponseEntity.status(status).body(
                new ErrorResponse(Instant.now(),
                        status.value(),
                        error,
                        msg,
                        request.getRequestURI()));
    }

    /* ---------- functional errors ---------- */

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> duplicate(DuplicateResourceException ex,
                                                   HttpServletRequest req) {
        return build(HttpStatus.CONFLICT, "Duplicate", ex.getMessage(), req);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> notFound(ResourceNotFoundException ex,
                                                  HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, "Not-Found", ex.getMessage(), req);
    }

    @ExceptionHandler(ExternalApiException.class)
    public ResponseEntity<ErrorResponse> external(ExternalApiException ex,
                                                  HttpServletRequest req) {
        return build(HttpStatus.BAD_GATEWAY, "Up-stream-Failure", ex.getMessage(), req);
    }

    /* ---------- low-level Spring / validation ---------- */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validation(MethodArgumentNotValidException ex,
                                                    HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, "Validation", ex.getMessage(), req);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> jpa(DataIntegrityViolationException ex,
                                             HttpServletRequest req) {
        // fallback, should rarely happen now
        return build(HttpStatus.CONFLICT, "Data-Integrity", ex.getMostSpecificCause().getMessage(), req);
    }

    /* ---------- unknown ---------- */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> generic(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Server-Error", ex.getMessage(), req);
    }
}
