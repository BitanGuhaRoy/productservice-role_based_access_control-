package com.example.productservice.repository;

import com.example.productservice.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product save(Product product);

    Optional<Product> findById(Long id);

    Page<Product> findAll(Pageable pageable);

}
