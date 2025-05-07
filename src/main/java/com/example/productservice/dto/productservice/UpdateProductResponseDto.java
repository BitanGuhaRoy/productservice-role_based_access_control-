package com.example.productservice.dto.productservice;

import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class UpdateProductResponseDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
    private String category;
    private Rating rating;
    public static UpdateProductResponseDto createProductResponseDto(Product product) {
        UpdateProductResponseDto updateProductResponseDto = new UpdateProductResponseDto();
        updateProductResponseDto.setId(product.getId());
        updateProductResponseDto.setName(product.getName());
        updateProductResponseDto.setDescription(product.getDescription());
        updateProductResponseDto.setPrice(product.getPrice());
        updateProductResponseDto.setImage(product.getImage());
        updateProductResponseDto.setCategory(product.getCategory().getName());
        Rating rating = new Rating();
        rating.setCount(product.getRatingCount());
        rating.setRate(product.getRatingValue());
        updateProductResponseDto.setRating(rating);
        return updateProductResponseDto;
    }
}
