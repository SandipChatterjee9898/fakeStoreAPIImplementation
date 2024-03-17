package dev.sandip.fakestoreapiimplementation.Exceptions;

public class ProductNotFoundForDeletionException extends Exception{
    public ProductNotFoundForDeletionException (String message){
        super(message);
    }
}
