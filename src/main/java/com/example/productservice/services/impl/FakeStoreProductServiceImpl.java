package com.example.productservice.services.impl;

import com.example.productservice.dto.fakestore.CreateProductFakeStoreRequestDto;
import com.example.productservice.dto.fakestore.CreateProductFakeStoreResponseDto;
import com.example.productservice.dto.fakestore.UpdateProductFakeStoreRequestDto;
import com.example.productservice.dto.fakestore.UpdateProductFakeStoreResponseDto;
import com.example.productservice.dto.productservice.GetProductResponseDto;
import com.example.productservice.model.Product;
import com.example.productservice.pojo.Rating;
import com.example.productservice.services.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl implements ProductService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Product createProduct(Product product) {
        CreateProductFakeStoreRequestDto createProductFakeStoreRequestDto = new CreateProductFakeStoreRequestDto();
        createProductFakeStoreRequestDto.setCategory(product.getCategory().getName());
        createProductFakeStoreRequestDto.setDescription(product.getDescription());
        createProductFakeStoreRequestDto.setTitle(product.getImage());
        createProductFakeStoreRequestDto.setPrice(product.getPrice());
        createProductFakeStoreRequestDto.setImage(product.getImage());
        Rating rating = new Rating();
        rating.setRate(product.getRatingValue());
        rating.setCount(product.getRatingCount());
        createProductFakeStoreRequestDto.setRating(rating);
        CreateProductFakeStoreResponseDto createProductFakeStoreResponseDto = restTemplate.postForEntity(
                "https://fakestoreapi.com/products",
                createProductFakeStoreRequestDto, CreateProductFakeStoreResponseDto.class).getBody();

        return createProductFakeStoreResponseDto.toProduct();
    }

    @Override
    public Page<Product> getProducts(int pageNumber, int pageSize){
        ArrayList<?> list = restTemplate.getForObject("https://fakestoreapi.com/products", ArrayList.class);
        List<Product> products = new ArrayList<>();
        List<CreateProductFakeStoreResponseDto> dtoList = objectMapper.convertValue(
                list, new TypeReference<List<CreateProductFakeStoreResponseDto>>() {
                }
        );
        for (CreateProductFakeStoreResponseDto createProductFakeStoreResponseDto : dtoList) {
            products.add(createProductFakeStoreResponseDto.toProduct());
        }
        return new PageImpl<>(products);
    }

    @Override
    public Product getProduct(Long id) {
        GetProductResponseDto getProductResponseDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, GetProductResponseDto.class);
        return getProductResponseDto.toProduct();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        UpdateProductFakeStoreRequestDto updateProductFakeStoreRequestDto = new UpdateProductFakeStoreRequestDto();
        updateProductFakeStoreRequestDto.setId(id);
        updateProductFakeStoreRequestDto.setTitle(product.getName());
        updateProductFakeStoreRequestDto.setDescription(product.getDescription());
        updateProductFakeStoreRequestDto.setPrice(product.getPrice());
        updateProductFakeStoreRequestDto.setImage(product.getImage());
        updateProductFakeStoreRequestDto.setCategory(product.getCategory().getName());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<UpdateProductFakeStoreRequestDto> requestEntity = new HttpEntity<>(updateProductFakeStoreRequestDto, headers);
        ResponseEntity<UpdateProductFakeStoreResponseDto> responseEntity = restTemplate.exchange(
                URI.create("https://fakestoreapi.com/products/" + id), HttpMethod.PUT, requestEntity, UpdateProductFakeStoreResponseDto.class
        );
        return responseEntity.getBody().toProduct();
    }

    public UpdateProductFakeStoreResponseDto put(URI url, @Nullable Object request) throws RestClientException {
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request);
        return (UpdateProductFakeStoreResponseDto) restTemplate.execute(url, HttpMethod.PUT, requestCallback, (ResponseExtractor) null);
    }


}