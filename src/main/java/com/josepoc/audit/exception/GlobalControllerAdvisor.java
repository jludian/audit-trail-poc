package com.josepoc.audit.exception;

import java.time.LocalDateTime;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvisor {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(errorResponse(e, request));
    }

    public Map<String, Object> errorResponse(Exception e, HttpServletRequest request) {
        return Map.of(
            "timestamp", LocalDateTime.now().toString(),
            "message", e.getMessage(),
            "resource", request.getPathInfo(),
            "path", request.getContextPath() + request.getServletPath()
        );
    }
}
