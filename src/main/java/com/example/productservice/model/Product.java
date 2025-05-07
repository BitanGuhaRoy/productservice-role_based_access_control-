package com.example.productservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Product extends BaseModel implements Serializable {
    private String name;
    private String description;
    private Double price;
    private String image;
    private double ratingValue;
    private int ratingCount;
    @ManyToOne
    @JsonIgnore
    private Category category;

}
