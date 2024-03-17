package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.DTOs.FakeStoreCategoryDTO;
import dev.sandip.fakestoreapiimplementation.DTOs.FakeStoreProductDTO;
import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
   // @Autowired
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" +productId, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProduct = fakeStoreProductResponse.getBody();
        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("The product with id "+productId+" does not exist. Please try with a different Product_ID");
        }
            return fakeStoreProduct.makeProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDTO[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDTO[].class);
        List<Product> finalList = new ArrayList<>();
        for(FakeStoreProductDTO i : fakeStoreProductDtos){
            finalList.add(i.makeProduct());
        }
        return finalList;
    }

    @Override
    public Product createNewProduct(String title, String description, String image, Long price, String category) {
        FakeStoreProductDTO fakeStoreProduct = new FakeStoreProductDTO();
        fakeStoreProduct.setTitle(title);
        fakeStoreProduct.setCategory(category);
        fakeStoreProduct.setImageUrl(image);
        fakeStoreProduct.setDescription(description);
        fakeStoreProduct.setPrice(price);
        FakeStoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDTO.class);
        return response.makeProduct();
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundForDeletionException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakestoreProduct = fakeStoreProductResponse.getBody();
        if(fakestoreProduct == null){
            throw new ProductNotFoundForDeletionException("The product with id "+productId+" does not exist. Please try to delete a different product.");
        }
        restTemplate.delete("https://fakestoreapi.com/products/"+productId);
        return null;
    }

    @Override
    public List<Category> getAllCategory() {
        List<String> categories = restTemplate.getForObject("https://fakestoreapi.com/products/categories", List.class);
        //FakeStoreCategoryDto[] fakeStoreCategoryDtos = restTemplate.getForObject("https://fakestoreapi.com/products/categories", FakeStoreCategoryDto.class);
        List<Category> finalList = new ArrayList<>();
        for(String i : categories){
            Category tempCategory = new Category();
            tempCategory.setTitle(i);
            finalList.add(tempCategory);
        }
        return finalList;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category) {
        List<Product> productList = new ArrayList<>();
        List<FakeStoreProductDTO> fakeStoreProductDto = restTemplate.exchange("https://fakestoreapi.com/products/category/"+category, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDTO>>() {
        }).getBody();

        for(FakeStoreProductDTO i : fakeStoreProductDto){
            Product tempProduct = new Product();
            Category tempCategory = new Category();
            tempProduct.setId(i.getId());
            tempProduct.setTitle(i.getTitle());
            tempProduct.setDescription(i.getDescription());
            tempProduct.setPrice(i.getPrice());
            tempCategory.setTitle(i.getCategory());
            tempProduct.setProductCategory(tempCategory);
            tempProduct.setImageUrl(i.getImageUrl());
            productList.add(tempProduct);
        }
        return productList;
    }

    @Override
    public Product updateProduct(Long productId, String title, String description, String image, Long price, String category) {
       FakeStoreProductDTO newFakeStoreProduct = new FakeStoreProductDTO();
       newFakeStoreProduct.setId(productId);
       newFakeStoreProduct.setTitle(title);
       newFakeStoreProduct.setDescription(description);
       newFakeStoreProduct.setImageUrl(image);
       newFakeStoreProduct.setPrice(price);
       newFakeStoreProduct.setCategory(category);

       restTemplate.put("https://fakestoreapi.com/products/"+productId, newFakeStoreProduct, FakeStoreCategoryDTO.class);
       return newFakeStoreProduct.makeProduct();
    }
    @Override
    public Product replaceProduct(Long productId, String title, String description, String image, Long price, String category) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDtoResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTO.class);
        FakeStoreProductDTO fakeStoreProduct = fakeStoreProductDtoResponse.getBody();
        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException("The product with id " + productId + " does not exist. Please try with a different Product_ID");
        }
        FakeStoreProductDTO updatedProduct = fakeStoreProduct;
        updatedProduct.setId(productId);
        if (title != null) {
            updatedProduct.setTitle(title);
        }
        if (description != null) {
            updatedProduct.setDescription(description);
        }
        if (image != null) {
//            updatedProduct.setImageUrl(image);
            updatedProduct.setImageUrl(image);
        }
        if (price != null) {
            updatedProduct.setPrice(price);
        }
        if (category != null) {
            updatedProduct.setCategory(category);
        }
        HttpEntity entityForExchange = new HttpEntity(updatedProduct);
        FakeStoreProductDTO response = restTemplate.exchange("https://fakestoreapi.com/products/" + productId, HttpMethod.PUT, entityForExchange, FakeStoreProductDTO.class).getBody();
        return response.makeProduct();
    }
}
