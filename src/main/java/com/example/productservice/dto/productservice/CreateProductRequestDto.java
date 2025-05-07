package com.example.productservice.dto.productservice;

import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private Double price;
    private String image;
    private String category;
    private Rating rating;

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImage(this.image);
        Category category = new Category();
        category.setName(this.category);
        product.setCategory(category);
//        Rating productRating = new ProductRating();
//        productRating.setRate(this.rating.getRate());
//        productRating.setCount(this.rating.getCount());
//        product.setRating(productRating);
        product.setRatingCount(this.rating.getCount());
        product.setRatingValue(this.rating.getRate());
            return product;
    }

}
