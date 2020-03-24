package com.otocar.otocar.controller;

import com.otocar.otocar.exceptions.ErrorJson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorJson> handleNumberFormat(Exception e){
        return new ResponseEntity<>(new ErrorJson(e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorJson> handleHttpMessageNotReadable(Exception e){
        return new ResponseEntity<>(new ErrorJson(e.getMessage(),null), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorJson>> handleConstraintViolationException(ConstraintViolationException  e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();

        List<ErrorJson> errorJsonList = new ArrayList<>();
        constraintViolations.forEach(constraintViolation -> errorJsonList.add(new ErrorJson(constraintViolation.getMessage(),constraintViolation.getPropertyPath().toString())));
        return new ResponseEntity<>(errorJsonList,HttpStatus.FAILED_DEPENDENCY);
    }
}
