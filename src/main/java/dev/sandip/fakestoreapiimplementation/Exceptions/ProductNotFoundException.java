package dev.sandip.fakestoreapiimplementation.Exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException (String message){
        super(message);
    }
}
