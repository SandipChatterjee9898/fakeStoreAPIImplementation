package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);
    List<Product> getProducts();
}
