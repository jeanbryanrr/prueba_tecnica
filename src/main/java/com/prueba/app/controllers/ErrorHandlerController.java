package com.prueba.app.controllers;

import com.prueba.app.exceptions.AppException;
import com.prueba.app.model.ErrorApp;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(AppException.class)
    public ErrorApp exceptionError(Exception e) {
        return new ErrorApp(e.getMessage());
    }
}
