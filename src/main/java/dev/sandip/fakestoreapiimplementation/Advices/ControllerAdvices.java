package dev.sandip.fakestoreapiimplementation.Advices;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.dtos.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundForDeletionException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundForDeletionException(ProductNotFoundForDeletionException exception){
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage(exception.getMessage());
        return  new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
