package com.qlBanSach.BookManager.Convertor;

import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Response.CartItemResponse;
import com.qlBanSach.BookManager.Model.Response.CartResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartConvertor {
    private final ModelMapper modelMapper;

    public CartResponse entityToResponse(CartEntity cartEntity) {
        CartResponse response = modelMapper.map(cartEntity, CartResponse.class);
        response.setCartItemResponses(cartEntity.getItems().stream()
                .map(item -> modelMapper.map(item, CartItemResponse.class)).toList());
        return response;
    }
}
