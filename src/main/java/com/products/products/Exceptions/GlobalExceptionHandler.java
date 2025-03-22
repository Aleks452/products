package com.products.products.Exceptions;

import jakarta.persistence.PersistenceException;
import java.sql.SQLNonTransientConnectionException;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.HibernateException;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  // Exception become returns mandatory fields in JSON code

  @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
  public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {

    BindingResult bindingResult;
    // obtaining result
    if (ex instanceof MethodArgumentNotValidException) {
      bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
    } else {
      bindingResult = ((BindException) ex).getBindingResult();
    }

    // mapping errors
    Map<String, String> errors = new HashMap<>();

    for (FieldError error : bindingResult.getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    // response with 400 status
    return ResponseEntity.badRequest().body(errors);
  }

  // personalizated exceptions

  @ExceptionHandler(CriterialNotFoundException.class)
  public ResponseEntity<String> handleDocumentNotFoundException(CriterialNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  // other exceptions from system

  @ExceptionHandler(DataAccessException.class)
  public ResponseEntity<String> handleDatabaseException(DataAccessException ex) {
    return new ResponseEntity<>(
        "Database error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(SQLNonTransientConnectionException.class)
  public ResponseEntity<String> handleDatabaseConnectionException(
      SQLNonTransientConnectionException ex) {
    return new ResponseEntity<>(
        "Database connection failed: " + ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler({
    JpaSystemException.class,
    HibernateException.class,
    PersistenceException.class
  })
  public ResponseEntity<String> handleJpaException(RuntimeException ex) {
    return new ResponseEntity<>(
        "JPA/Hibernate error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ConversionFailedException.class)
  public ResponseEntity<String> handleConversionException(ConversionFailedException ex) {
    return new ResponseEntity<>(
        "Error converting data: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<String> handleArgumentMismatch(MethodArgumentTypeMismatchException ex) {
    return new ResponseEntity<>("Invalid parameter: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  // Manejo de cualquier otra excepción no contemplada
  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return new ResponseEntity<>(
        "Unexpected error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
