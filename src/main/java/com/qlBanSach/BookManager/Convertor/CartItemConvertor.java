package com.qlBanSach.BookManager.Convertor;

import com.qlBanSach.BookManager.Model.Entity.CartItemEntity;
import com.qlBanSach.BookManager.Model.Response.CartItemResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartItemConvertor {
    private final ModelMapper modelMapper;
    private final BookConvertor bookConvertor;

    public CartItemResponse entityToResponse(CartItemEntity cartItemEntity) {
        CartItemResponse cartItemResponse = modelMapper.map(cartItemEntity, CartItemResponse.class);
        cartItemResponse.setBook(bookConvertor.entityToResponse(cartItemEntity.getBook()));
        cartItemResponse.setUnitPrice(cartItemEntity.getBook().getPrice());
        return cartItemResponse;
    }
}
