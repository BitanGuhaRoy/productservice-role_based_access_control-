package com.example.productservice.dto.fakestore;

import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class CreateProductFakeStoreRequestDto {

    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private Rating rating;

}
