package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Entity.CartItemEntity;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Service.Cart.CartService;
import com.qlBanSach.BookManager.Service.Cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/carts")
public class CartController {
    private final ICartService cartService;

    @PostMapping(value = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse saveCart(@RequestBody CartEntity cart) {
        CartEntity cartEntity = cartService.save(cart);
        return APIResponse.builder()
                .message("CREATE SUCCESS")
                .response(cartEntity)
                .build();
    }

    @PostMapping(value = "/cartItems/{bookIds}")
    public APIResponse addBookToCart(@PathVariable String bookIds) {
        List<String> listBookId = new ArrayList<>();
        listBookId.add(bookIds);
        cartService.addCartItem(listBookId, SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        return APIResponse.builder()
                .message("SUCCESS")
                .build();
    }

    @DeleteMapping(value = "/{cartItemId}")
    public APIResponse removeCartItem(@PathVariable(value = "cartItemId") List<Long>cartItemId) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.removeCartItem(email, cartItemId);
        return APIResponse.builder()
                .message("SUCCESS")
                .build();
    }

    @PutMapping(value = "/{cartItemId}")
    public APIResponse updateCartItemQuantity(@PathVariable(value = "cartItemId") Long cartItemId, @RequestParam(value = "quantity") Long quantity) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        cartService.updateItemQuantity(email, cartItemId, quantity);
        return APIResponse.builder()
                .message("SUCCESS")
                .build();
    }
}
