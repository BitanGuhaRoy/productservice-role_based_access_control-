package com.example.productservice.dto.productservice;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class CreateProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private Category category;
    private Rating rating;
    public static CreateProductResponseDto createProductResponseDto(Product product) {

        CreateProductResponseDto createProductResponseDto = new CreateProductResponseDto();
        createProductResponseDto.setId(product.getId());
        createProductResponseDto.setName(product.getName());
        createProductResponseDto.setDescription(product.getDescription());
        createProductResponseDto.setPrice(product.getPrice());
        createProductResponseDto.setImage(product.getImage());
        createProductResponseDto.setCategory(product.getCategory());
        return createProductResponseDto;
    }


}
