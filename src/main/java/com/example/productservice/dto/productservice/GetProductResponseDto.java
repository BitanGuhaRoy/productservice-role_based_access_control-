package com.example.productservice.dto.productservice;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class GetProductResponseDto {
    private String title;
    private double price;
    private int id;
    private String image;
    private String description;
    private String category;
    private Rating rating;


    public Product toProduct()
    {
        Product product = new Product();
        product.setImage(this.image);
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
        product.setPrice(this.price);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setId((long) this.id);
        product.setRatingValue(this.rating.getRate());
        product.setRatingCount(this.rating.getCount());
        return product;

    }
}
