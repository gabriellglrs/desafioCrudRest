package com.spring.desafiocrudrest.exceptions.Handler;

import com.spring.desafiocrudrest.exceptions.Handler.error.CustomError;
import com.spring.desafiocrudrest.exceptions.Handler.error.ValidationError;
import com.spring.desafiocrudrest.exceptions.DatabaseException;
import com.spring.desafiocrudrest.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(ResourceNotFoundException.class)
     public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
          HttpStatus status = HttpStatus.NOT_FOUND;
          CustomError err = CustomError.builder()
                  .timestamp(Instant.now())
                  .status(status.value())
                  .error(e.getMessage())
                  .path(request.getRequestURI())
                  .build();
          return ResponseEntity.status(status).body(err);
     }

     @ExceptionHandler(DatabaseException.class)
     public ResponseEntity<CustomError> database(DatabaseException e, HttpServletRequest request){
          HttpStatus status = HttpStatus.BAD_REQUEST;
          CustomError err = CustomError.builder()
                  .timestamp(Instant.now())
                  .status(status.value())
                  .error(e.getMessage())
                  .path(request.getRequestURI())
                  .build();
          return ResponseEntity.status(status).body(err);
     }

     @ExceptionHandler(MethodArgumentNotValidException.class)
     public ResponseEntity<CustomError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
          HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
          ValidationError err = ValidationError.builder()
                    .timestamp(Instant.now())
                    .status(status.value())
                    .error("Erro de validação")
                    .path(request.getRequestURI())
                    .build();

          for (FieldError f : e.getBindingResult().getFieldErrors()) {
               err.addError(f.getField(), f.getDefaultMessage());
          }

          return ResponseEntity.status(status).body(err);
     }

}
