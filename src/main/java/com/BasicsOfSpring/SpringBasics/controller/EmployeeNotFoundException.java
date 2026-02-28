package com.BasicsOfSpring.SpringBasics.controller;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String s) {
        super (s);
    }
}
