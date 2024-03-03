package dev.sandip.fakestoreapiimplementation.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String imageUrl;
    private Category productCategory;
}
