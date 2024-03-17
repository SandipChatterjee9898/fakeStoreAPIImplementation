package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundException;
import dev.sandip.fakestoreapiimplementation.Exceptions.ProductNotFoundForDeletionException;
import dev.sandip.fakestoreapiimplementation.dtos.FakeStoreCategoryDto;
import dev.sandip.fakestoreapiimplementation.dtos.FakeStoreProductDto;
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
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" +productId, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProduct = fakeStoreProductResponse.getBody();
        if(fakeStoreProduct == null){
            throw new ProductNotFoundException("The product with id "+productId+" does not exist. Please try with a different Product_ID");
        }
            return fakeStoreProduct.makeProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        List<Product> finalList = new ArrayList<>();
        for(FakeStoreProductDto i : fakeStoreProductDtos){
            finalList.add(i.makeProduct());
        }
        return finalList;
    }

    @Override
    public Product createNewProduct(String title, String description, String image, Long price, String category) {
        FakeStoreProductDto fakeStoreProduct = new FakeStoreProductDto();
        fakeStoreProduct.setTitle(title);
        fakeStoreProduct.setCategory(category);
        fakeStoreProduct.setImageUrl(image);
        fakeStoreProduct.setDescription(description);
        fakeStoreProduct.setPrice(price);
        FakeStoreProductDto response = restTemplate.postForObject("https://fakestoreapi.com/products", fakeStoreProduct, FakeStoreProductDto.class);
        return response.makeProduct();
    }

    @Override
    public Product deleteProduct(Long productId) throws ProductNotFoundForDeletionException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/"+productId, FakeStoreProductDto.class);
        FakeStoreProductDto fakestoreProduct = fakeStoreProductResponse.getBody();
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
        List<FakeStoreProductDto> fakeStoreProductDto = restTemplate.exchange("https://fakestoreapi.com/products/category/"+category, HttpMethod.GET, null, new ParameterizedTypeReference<List<FakeStoreProductDto>>() {
        }).getBody();

        for(FakeStoreProductDto i : fakeStoreProductDto){
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
       FakeStoreProductDto newFakeStoreProduct = new FakeStoreProductDto();
       newFakeStoreProduct.setId(productId);
       newFakeStoreProduct.setTitle(title);
       newFakeStoreProduct.setDescription(description);
       newFakeStoreProduct.setImageUrl(image);
       newFakeStoreProduct.setPrice(price);
       newFakeStoreProduct.setCategory(category);

       restTemplate.put("https://fakestoreapi.com/products/"+productId, newFakeStoreProduct, FakeStoreCategoryDto.class);
       return newFakeStoreProduct.makeProduct();
    }
    @Override
    public Product replaceProduct(Long productId, String title, String description, String image, Long price, String category) throws ProductNotFoundException {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponse = restTemplate.getForEntity("https://fakestoreapi.com/products/" + productId, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProduct = fakeStoreProductDtoResponse.getBody();
        if (fakeStoreProduct == null) {
            throw new ProductNotFoundException("The product with id " + productId + " does not exist. Please try with a different Product_ID");
        }
        FakeStoreProductDto updatedProduct = fakeStoreProduct;
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
        FakeStoreProductDto response = restTemplate.exchange("https://fakestoreapi.com/products/" + productId, HttpMethod.PUT, entityForExchange, FakeStoreProductDto.class).getBody();
        return response.makeProduct();
    }
}
