package com.example.productservice.dto.productservice;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class UpdateProductRequestDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private Rating rating;
    public Product toProduct() {
        Product product = new Product();
        product.setId(id);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
        product.setImage(this.image);
        return product;
    }

}
