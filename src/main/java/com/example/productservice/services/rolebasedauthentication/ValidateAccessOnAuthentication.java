package com.example.productservice.services.rolebasedauthentication;

public interface ValidateAccessOnAuthentication {

    boolean validateAccessOnGetAllProducts(String token);
}
