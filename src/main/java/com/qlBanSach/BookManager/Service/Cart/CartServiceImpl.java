package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Response.CartResponse;
import com.qlBanSach.BookManager.Repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements ICartService {
    private final ICartRepository cartRepository;

    @Override
    public CartResponse createCart() {
        return null;
    }

    @Override
    public CartResponse getCartById(Long id) {
        return null;
    }

    @Override
    public void deleteCartById(List<Long> id) {

    }

    @Override
    public List<CartResponse> getAllCart() {
        return List.of();
    }
}
