package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Entity.CartEntity;

import java.util.List;

public interface ICartService {
    CartEntity getCart(String email);
    void clearCart(String email);
    Double getTotalPrice(String email);
    CartEntity save(CartEntity cart);
    void addCartItem(List<String> cartItems, String email);
    void removeCartItem(String email, List<Long> cartItemIds);
    void updateItemQuantity(String email, Long itemId, Long quantity);
}
