package com.example.productservice.pojo;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class Rating {
    private double rate;
    private int count;
}
