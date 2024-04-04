package com.husseinhamadi.LMS.advice;

import com.husseinhamadi.LMS.exception.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> ArgumentsNotValidException(MethodArgumentNotValidException ex){
        //map for field name and error associated to it
        Map<String,String> errorMessage=new HashMap<>();

        //getting field names and associated errors from exception
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            //storing in map
            errorMessage.put(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    public Map<String, String> NotFoundException(NotFoundException ex){
        Map<String,String> errorMessage=new HashMap<>();
        errorMessage.put("errorMessage", ex.getMessage());

        return errorMessage;
    }


}
