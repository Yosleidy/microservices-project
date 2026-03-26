package com.example.order_service.config;

import com.example.order_service.exception.ProductNotFoundException;
import com.example.order_service.exception.UserNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomFeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {

        if (response.status() == 404) {

            if (methodKey.contains("UserClient")) {
                return new UserNotFoundException(null);
            }

            if (methodKey.contains("ProductClient")) {
                return new ProductNotFoundException(null);
            }
        }


        return feign.FeignException.errorStatus(methodKey, response);
    }
}