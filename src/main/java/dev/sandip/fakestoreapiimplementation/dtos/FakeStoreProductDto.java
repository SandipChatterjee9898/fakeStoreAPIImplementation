package dev.sandip.fakestoreapiimplementation.dtos;

import dev.sandip.fakestoreapiimplementation.models.Category;
import dev.sandip.fakestoreapiimplementation.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Long price;
    private String imageUrl;

    public Product makeProduct(){
        Product newProduct = new Product();
        newProduct.setId(id);
        newProduct.setTitle(title);
        newProduct.setDescription(description);
        newProduct.setPrice(price);
        newProduct.setImageUrl(imageUrl);

        Category tempCategory = new Category();
        tempCategory.setTitle(category);

        newProduct.setProductCategory(tempCategory);
        return newProduct;
    }
}
