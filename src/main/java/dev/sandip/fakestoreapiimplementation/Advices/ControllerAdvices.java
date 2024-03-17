package dev.sandip.fakestoreapiimplementation.Advices;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.DTOs.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerAdvices {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundException(ProductNotFoundException exception){
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ProductNotFoundForDeletionException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFoundForDeletionException(ProductNotFoundForDeletionException exception){
        ErrorDTO errorDto = new ErrorDTO();
        errorDto.setMessage(exception.getMessage());
        return  new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }
}
