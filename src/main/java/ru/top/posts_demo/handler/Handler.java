package ru.top.posts_demo.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.NoSuchElementException;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(value = NoSuchElementException.class)
    protected ResponseEntity<String> handleConflict(
            RuntimeException ex, WebRequest request) {
        return ResponseEntity.internalServerError().body(ex.getMessage());
    }
}
