package com.example.productservice.services;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    Page<Product> getProducts(int pageNumber, int pageSize);

    Product getProduct(Long id);

    Product updateProduct(Long id, Product product) throws ProductNotFoundException;
}
