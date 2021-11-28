package com.example.demo.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import com.example.demo.dto.BaseResponse;
import com.example.demo.dto.request.CheckFeeRequest;
import com.example.demo.dto.request.CreateOrderRequest;
import com.example.demo.dto.response.CheckFeeResponse;
import com.example.demo.dto.response.CreateOrderResponse;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order-api")
@Tag(name = "Order, fee", description = "Các api liên quan đến order, phí")
public interface OrderController {

    @PostMapping("/check-fee")
    BaseResponse<CheckFeeResponse> checkFeeOrder(@ModelAttribute CheckFeeRequest request);

    @PostMapping("/order-create")
    BaseResponse<CreateOrderResponse> creatOrder(@RequestBody CreateOrderRequest request);
}
