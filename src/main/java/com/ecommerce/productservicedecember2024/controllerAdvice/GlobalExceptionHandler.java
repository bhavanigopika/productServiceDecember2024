package com.ecommerce.productservicedecember2024.controllerAdvice;

import com.ecommerce.productservicedecember2024.exceptions.ProductNotFoundException;
import com.ecommerce.productservicedecember2024.models.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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

    //we want to get message from service -> controller -> controllerAdvice -> client

    //But here, we didn't mention any parameter. So, what we do in body("ProductNotFoundException from GlobalExceptionHandler"), it returns the response to client

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<String> handleProductNotFoundException(){
//        ResponseEntity<String> responseEntity = new ResponseEntity<>("ProductNotFoundException from GlobalExceptionHandler", HttpStatus.BAD_REQUEST);
//        return responseEntity;
//    }

    //service -> controller -> controllerAdvice -> client
    //But here, we mention some parameter. So, what we return in body (i.e) e.getMessage, it returns the response to client
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        ResponseEntity<String> responseEntity = ResponseEntity.badRequest().body(e.getMessage());
        return responseEntity;
    }
}
