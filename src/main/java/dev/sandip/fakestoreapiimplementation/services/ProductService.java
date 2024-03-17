package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    //Product createNewProduct(String title, String description, String image, Double price, String category);

    Product createNewProduct(String title, String description, String image, Long price, String category);
    Product deleteProduct(Long productId) throws ProductNotFoundForDeletionException;
    List<Category> getAllCategory();
    List<Product> getAllProductsByCategory(String category);
    Product updateProduct(Long productId, String title, String description, String image, Long price, String category);
    Product replaceProduct(Long productId, String title, String description, String image, Long price, String category) throws ProductNotFoundException;
}
