package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.Entity.OrderEntity;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Service.Cart.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/orders")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping(value = "/")
    public APIResponse createOrder() {
        OrderEntity order = orderService.createOrder();
        return APIResponse.builder()
                .message("SUCCESS")
                .response(order)
                .build();
    }
}
