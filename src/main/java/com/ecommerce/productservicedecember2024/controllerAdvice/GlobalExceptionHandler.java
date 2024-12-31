package com.ecommerce.productservicedecember2024.controllerAdvice;

import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    //Let's say, it returns string
    //what type of exception it returns
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<String> handleArithmeticException(){
        //Here, it returns responseEntity of String
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Arithmetic Exception from GlobalExceptionHandler", HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<String> handleArrayIndexOutOfBoundsException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>("ArrayIndexOutOfBoundsException from GlobalExceptionHandler", HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> ProductNotFoundException(){
        ResponseEntity<String> responseEntity = new ResponseEntity<>("ProductNotFoundException from GlobalExceptionHandler", HttpStatus.BAD_REQUEST);
        return responseEntity;
    }
}
