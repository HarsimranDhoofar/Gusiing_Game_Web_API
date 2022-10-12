/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bullsandcows.controllers;

import java.sql.SQLIntegrityConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 *
 * @author dhoofa
 */

@ControllerAdvice
@RestController
public class BullsAndCowsControllerExceptionHandler extends ResponseEntityExceptionHandler  {
    private static final String CONSTRAINT_MESSAGE = "Please check if all the arguments have valid values and try again. Noting saved";

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public final ResponseEntity<Error> handleSqlException(
            SQLIntegrityConstraintViolationException ex,
            WebRequest request) {

        Error err = new Error();
        err.setMessage(CONSTRAINT_MESSAGE);
        return new ResponseEntity<>(err, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @ExceptionHandler(value = {Exception.class})
       public ResponseEntity<Object> exception(Exception exception) {
           Error err = new Error();
            err.setMessage(CONSTRAINT_MESSAGE);
            return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
}
    
}
