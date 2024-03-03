package dev.sandip.fakestoreapiimplementation.services;

import dev.sandip.fakestoreapiimplementation.dtos.FakeStoreProductDto;
import dev.sandip.fakestoreapiimplementation.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class FakeStoreProductService implements ProductService{
   // @Autowired
    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //public FakeStoreProductService() {

    //}

    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("https://fakestoreapi.com/products/" +productId, FakeStoreProductDto.class);
        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}
