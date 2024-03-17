package dev.sandip.fakestoreapiimplementation.controllers;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.dtos.ReplaceProductDto;
import dev.sandip.fakestoreapiimplementation.dtos.UpdateProductDto;
import dev.sandip.fakestoreapiimplementation.dtos.createNewProductDto;
import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;
import dev.sandip.fakestoreapiimplementation.services.FakeStoreProductService;
import dev.sandip.fakestoreapiimplementation.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
     private final ProductService productService;
     public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
         this.productService = productService;
     }
     @GetMapping( "/products/{id}")
    public Product getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
         return productService.getSingleProduct(productId);
     }
     @PostMapping("/products")
    public Product createNewProduct(@RequestBody createNewProductDto request){
         return productService.createNewProduct(
                 request.getTitle(),
                 request.getDescription(),
                 request.getImage(),
                 request.getPrice(),
                 request.getCategory()
         );
     }
     @DeleteMapping("products/{id}")
    public Product deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundForDeletionException {
         return productService.deleteProduct(productId);
     }
    @GetMapping("/products")
    public List<Product> getAllProducts(){
         return productService.getAllProducts();
    }
    @GetMapping("products/categories")
    public List<Category> getAllCategories(){
         return productService.getAllCategory();
    }
    @GetMapping("products/category/{category}")
    public List<Product> getAllProductsByCategory(@PathVariable("category") String category){
         return productService.getAllProductsByCategory(category);
    }
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody UpdateProductDto request){
         return productService.updateProduct(
                 id,
                 request.getTitle(),
                 request.getDescription(),
                 request.getImage(),
                 request.getPrice(),
                 request.getCategory()
         );
    }
    @PatchMapping("/products/{id}")
    public  Product replaceProduct(@PathVariable("id") Long id, @RequestBody ReplaceProductDto request) throws ProductNotFoundException{
         return productService.replaceProduct(
                 id,
                 request.getTitle(),
                 request.getDescription(),
                 request.getImage(),
                 request.getPrice(),
                 request.getCategory()
         );
    }
}
