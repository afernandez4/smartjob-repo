package com.smartjob.users.exception;

import com.smartjob.users.dto.Response;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EntityExistsException.class)
  public ResponseEntity<Response> handleException(EntityExistsException exception) {
    Response response = new Response(exception.getMessage());
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response> handleValidationExceptions(MethodArgumentNotValidException ex) {
    return ResponseEntity.badRequest().body(new Response(
            ex.getBindingResult().getFieldErrors().stream()
                    .findFirst()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .orElse("Invalid password")
    ));
  }
}
