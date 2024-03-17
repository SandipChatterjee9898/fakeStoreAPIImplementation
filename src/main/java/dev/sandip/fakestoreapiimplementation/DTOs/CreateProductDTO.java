package dev.sandip.fakestoreapiimplementation.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    private String title;
    private String description;
    private String image;
    private Long price;
    private String category;
}
