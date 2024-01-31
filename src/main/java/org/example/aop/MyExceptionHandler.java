package org.example.aop;

import org.example.Exceptions.BadRequestException;
import org.example.Exceptions.MyHibernateException;
import org.example.Exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad request");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad request");
    }

    @ExceptionHandler(MyHibernateException.class)
    public ResponseEntity<String> handleHibernate(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hibernate exception");
    }
}
