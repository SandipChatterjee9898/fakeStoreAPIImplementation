package dev.sandip.fakestoreapiimplementation.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplaceProductDto {
    private String title;
    private String description;
    private String image;
    private Long price;
    private String category;
}
