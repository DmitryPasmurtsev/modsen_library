package com.modsen.books.exceptions.handler;

import com.modsen.books.exceptions.NotCreatedException;
import com.modsen.books.exceptions.NotFoundException;
import com.modsen.books.exceptions.WebClientException;
import com.modsen.books.exceptions.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.NOT_FOUND,
                        notFoundException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
    @ExceptionHandler(value = {NotCreatedException.class})
    public ResponseEntity<Object> handleNotCreatedException(NotCreatedException notCreatedException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.BAD_REQUEST,
                        notCreatedException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
    @ExceptionHandler(value = {WebClientException.class})
    public ResponseEntity<Object> handleNotCreatedException(WebClientException webClientException){
        ExceptionResponse response=
                new ExceptionResponse( HttpStatus.BAD_REQUEST,
                        webClientException.getMessage()
                );
        return new ResponseEntity<>(response,response.getStatus());
    }
}
