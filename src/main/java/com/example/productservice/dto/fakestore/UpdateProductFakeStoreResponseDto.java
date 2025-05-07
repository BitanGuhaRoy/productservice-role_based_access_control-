package com.example.productservice.dto.fakestore;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.Data;

@Data
public class UpdateProductFakeStoreResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;
    private Rating rating;
    public Product toProduct() {
        Product product = new Product();
        Category category = new Category();
        product.setId(this.id);
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setName(this.category);
        product.setCategory(category);
        product.setImage(this.image);
        return product;
    }
}
