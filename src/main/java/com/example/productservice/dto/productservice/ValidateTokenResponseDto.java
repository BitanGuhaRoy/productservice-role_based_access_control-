package com.example.productservice.dto.productservice;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidateTokenResponseDto {

    private String username;
    private String email;
    List<RoleDto> roles;
}
