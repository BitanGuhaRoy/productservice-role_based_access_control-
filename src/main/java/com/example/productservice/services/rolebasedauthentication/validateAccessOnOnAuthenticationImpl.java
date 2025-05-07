package com.example.productservice.services.rolebasedauthentication;

import com.example.productservice.dto.productservice.RoleDto;
import com.example.productservice.dto.productservice.ValidateTokenResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class validateAccessOnOnAuthenticationImpl implements ValidateAccessOnAuthentication {


    @Autowired
    private RestTemplate restTemplate;

    @Override
    public boolean validateAccessOnGetAllProducts(String token) {
        String url = "http://localhost:8081/auth/validateToken";

        HttpHeaders headers = new HttpHeaders();
        headers.set("auth", token);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<ValidateTokenResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, entity, ValidateTokenResponseDto.class);
        ValidateTokenResponseDto body = response.getBody();
        try {
            for (RoleDto roleDto : body.getRoles()) {
                if (roleDto.getName().equals("ADMIN")) {
                    return true;
                }
            }
            return false;
        }
        catch (Exception e){
           return false;
        }

    }
}
