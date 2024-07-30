package com.api.booksManager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
class ControllerException extends RuntimeException {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleException(MethodArgumentNotValidException e) {

        MessageReturn generic = new MessageReturn(
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                e.getFieldError().getDefaultMessage(),
                "Um problema com este campo: " + e.getFieldError().getField());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generic);
    }

}