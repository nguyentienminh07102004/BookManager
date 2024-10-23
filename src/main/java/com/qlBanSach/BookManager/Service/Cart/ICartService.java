package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Response.CartResponse;

import java.util.List;

public interface ICartService {
    CartResponse createCart();
    CartResponse getCartById(Long id);
    void deleteCartById(List<Long> id);
    List<CartResponse> getAllCart();
}
