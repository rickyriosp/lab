package com.riosr.spring6restmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ricky
 * @created 2025-02-12
 */
@ControllerAdvice
public class ExceptionController {

//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<Object> handleNotFoundException() {
//        System.out.println("In exception handler");
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
}
