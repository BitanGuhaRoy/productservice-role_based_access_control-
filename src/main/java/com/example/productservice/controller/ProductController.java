package com.example.productservice.controller;

import com.example.productservice.dto.productservice.*;
import com.example.productservice.model.Product;
import com.example.productservice.services.ProductService;
import com.example.productservice.services.rolebasedauthentication.ValidateAccessOnAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    @Qualifier("dataBaseProductService")
    private ProductService productService;

    @Autowired
    private ValidateAccessOnAuthentication validateAccessOnAuthentication;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {

        return productService.getProduct(id);
    }



    @GetMapping("/")
    public ResponseEntity<GetAllProductsResponseDto> getProducts(@RequestHeader("token") String token, @RequestParam("pagenumber") int  pagenumber, @RequestParam("pagesize") int pagesize) {

        if(!validateAccessOnAuthentication.validateAccessOnGetAllProducts(token)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Page<Product> products = productService.getProducts(pagenumber, pagesize);
        GetAllProductsResponseDto responseDto = new GetAllProductsResponseDto(products);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping()
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto createProductRequestDto) {

        CreateProductResponseDto createProductResponseDto = CreateProductResponseDto.createProductResponseDto(
                productService.createProduct(createProductRequestDto.toProduct()));
//        return new ResponseEntity<>(createProductResponseDto, HttpStatus.ACCEPTED);
        return createProductResponseDto;

    }

    @PatchMapping("/{id}")
    public UpdateProductResponseDto updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequestDto updateProductRequestDto) throws Exception {
        Product product = productService.updateProduct(id, updateProductRequestDto.toProduct());

        return UpdateProductResponseDto.createProductResponseDto(product);
    }

}
