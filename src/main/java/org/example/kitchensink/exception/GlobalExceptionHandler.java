package org.example.kitchensink.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (e.g., @NotBlank, @Email, @Pattern violations)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        // Concatenate all validation error messages into a single string
        String errors = ex.getBindingResult().getAllErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        // Set Content-Type to text/plain
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");

        return new ResponseEntity<>(errors, headers, HttpStatus.BAD_REQUEST);
    }

    // Handle custom DuplicateEmailException
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<String> handleDuplicateEmailException(DuplicateEmailException ex, WebRequest request) {
        // Set Content-Type to text/plain
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");

        return new ResponseEntity<>(ex.getMessage(), headers, HttpStatus.CONFLICT);
    }

    // Global exception handler (fallback for other unhandled exceptions)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        // Set Content-Type to text/plain
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "text/plain");

        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
