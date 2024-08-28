package com.api.booksManager.exceptions;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@ControllerAdvice
class ControllerException extends RuntimeException {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MessageReturn> handleAllExceptions(
            Exception e, WebRequest request) {

        MessageReturn generic = new MessageReturn(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getMessage(),
                "Um problema com este campo:  " + e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generic);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<MessageReturn> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {

        MessageReturn generic = new MessageReturn(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getFieldError().getDefaultMessage(),
                "Um problema com este campo:  " + e.getFieldError().getField());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generic);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = "Por favor insira um Request Body com JSON válido";
        List<String> messages = new ArrayList<>(1);
        messages.add(message);
        return new ResponseEntity<>(new ExceptionResponse(messages, HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<MessageReturn> handleUnexpectedTypeException(UnexpectedTypeException e) {

        MessageReturn generic = new MessageReturn(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getLocalizedMessage(),
                "Um problema:  " + e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generic);
    }
}


