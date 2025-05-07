package com.example.productservice.dto.productservice;

import com.example.productservice.model.Product;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class GetAllProductsResponseDto {
    public Page<Product> products;

    public GetAllProductsResponseDto(Page<Product> products) {
        this.products = products;
    }
}
