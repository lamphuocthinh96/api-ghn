package com.example.demo.controller.impl;

import com.example.demo.controller.OrderController;
import com.example.demo.controller.OrderController;
import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.request.CheckFeeRequest;
import com.example.demo.dto.request.CreateOrderRequest;
import com.example.demo.dto.response.CheckFeeResponse;
import com.example.demo.dto.response.CreateOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderControllerImpl implements OrderController {

    @Value("${GHN.url}")
    public String ghnUrl;

    @Value("${GHN.token}")
    public String ghnToken;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public BaseResponse<CheckFeeResponse> checkFeeOrder(CheckFeeRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", ghnToken);
        HttpEntity<Object> entity = new HttpEntity<>(request, headers);
        ResponseEntity<Object> responseEntity = restTemplate.exchange(ghnUrl + "v2/shipping-order/fee", HttpMethod.POST, entity, Object.class);
        return BaseResponse.success(null);
    }

    @Override
    public BaseResponse<CreateOrderResponse> creatOrder(CreateOrderRequest request) {
        return null;
    }
}
