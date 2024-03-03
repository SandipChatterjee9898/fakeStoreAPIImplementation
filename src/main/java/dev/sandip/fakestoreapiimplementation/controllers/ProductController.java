package dev.sandip.fakestoreapiimplementation.controllers;

import dev.sandip.fakestoreapiimplementation.models.Product;
import dev.sandip.fakestoreapiimplementation.services.FakeStoreProductService;
import dev.sandip.fakestoreapiimplementation.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
     private final ProductService productService;
     public ProductController(FakeStoreProductService productService){
         this.productService = productService;
     }
     @GetMapping(value = "/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId){
         return productService.getSingleProduct(productId);
     }
}
