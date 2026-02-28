package com.BasicsOfSpring.SpringBasics;

import com.BasicsOfSpring.SpringBasics.controller.EmployeeNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationException {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public String handleException(EmployeeNotFoundException ex){
        return ex.getMessage();
    }
    @ExceptionHandler(Exception.class)
    public String handleException1(Exception ex){
        return "Exception";
    }
}
