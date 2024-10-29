package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Entity.OrderEntity;

public interface IOrderService {
    OrderEntity createOrder();
    void deleteOrder();
}
