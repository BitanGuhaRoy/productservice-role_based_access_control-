package com.example.productservice.services.impl;

import com.example.productservice.exceptions.ProductNotFoundException;
import com.example.productservice.model.Category;
import com.example.productservice.model.Product;
import com.example.productservice.repository.CategoryRepository;
import com.example.productservice.repository.ProductRepository;
import com.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dataBaseProductService")
public class SelfProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Product createProduct(Product product) {

        Category category = categoryRepository.save(product.getCategory());
        product.setCategory(category);
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize) {
        Sort sort = Sort.by("price").descending().and(Sort.by("name").ascending());
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize, sort));
    }

    @Override
    public Product getProduct(Long id) {

        Product product = (Product) redisTemplate.opsForHash().get("PRODUCT", "PRODUCT_"+id);
        if(product != null){
            return product;
        }
        Optional<Product> productOptional = productRepository.findById(id);
        if(!productOptional.isEmpty()) {
            redisTemplate.opsForHash().put("PRODUCT", "PRODUCT_" + id, productOptional.get());
            return productOptional.get();

        }
        return new Product();

    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product1 = optionalProduct.get();
            if (product.getDescription() != null) {
                product1.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                product1.setPrice(product.getPrice());
            }
            return product1;
        } else {
            throw new ProductNotFoundException("Product not found");
        }
    }
}
