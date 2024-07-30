package com.api.booksManager.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpInputMessage;

@Getter
@Setter
@AllArgsConstructor
public class MessageReturn {

    private String status;
    private String message;
    private String cause;

}
