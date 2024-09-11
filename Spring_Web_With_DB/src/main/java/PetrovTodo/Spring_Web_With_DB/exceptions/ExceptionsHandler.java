package PetrovTodo.Spring_Web_With_DB.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(BadRequestExeption.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)//Err 400
    public ErrorsPayload handleBadRequest(BadRequestExeption ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)//Err 404
    public ErrorsPayload handleNotFound(NotFoundException ex) {
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) //Err 500
    public ErrorsPayload handleGenericErors(Exception ex) {
        ex.printStackTrace();// Serve per catturare tutti gli errori e stamparli nella console
        return new ErrorsPayload(ex.getMessage(), LocalDateTime.now());
    }

}
