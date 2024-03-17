package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;
import dev.sandip.fakestoreapiimplementation.repositories.CategoryRepository;
import dev.sandip.fakestoreapiimplementation.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ownProductService")
public class OwnProductService implements ProductService{


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public OwnProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product createNewProduct(String title, String description, String image, Long price, String category) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);
        Category categoryFromDataBase = categoryRepository.findByTitle(category);
        if(categoryFromDataBase == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDataBase = categoryRepository.save(newCategory);
            //newCategory = categoryFromDataBase;
        }
        product.setProductCategory(categoryFromDataBase);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
     }

    @Override
    public Product deleteProduct(Long productId) {
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        //return productRepository.findByCategory_Title(category);
        return null;
    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String image, Long price, String category) {
        return null;
    }
    @Override
    public Product replaceProduct(Long productId, String title, String description, String image, Long price, String category) throws ProductNotFoundException {
        return null;
    }
}
