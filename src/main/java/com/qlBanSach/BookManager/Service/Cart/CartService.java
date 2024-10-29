package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Entity.CartItemEntity;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.ICartItemRepository;
import com.qlBanSach.BookManager.Repository.ICartRepository;
import com.qlBanSach.BookManager.Service.Book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final ICartRepository cartRepository;
    private final ICartItemRepository cartItemRepository;
    private final IBookService bookService;

    @Override
    public CartEntity getCart(String email) {
        CartEntity cart = cartRepository.findByUser_Email(email)
                .orElseThrow(() -> new DataInvalidException("Cart not found"));
        return cart;
    }

    @Override
    @Transactional
    public void clearCart(String email) {
        CartEntity cart = getCart(email);
        cart.getCartItems().forEach(item -> cart.removeItems(item.getId()));
        cartRepository.save(cart);
    }

    @Override
    public Double getTotalPrice(String email) {
        CartEntity cart = getCart(email);
        return cart.getTotalAmount();
    }

    @Override
    public CartEntity save(CartEntity cart) {
        if(cart.getId() != null) {
            cartRepository.findById(cart.getId())
                    .orElseThrow(() -> new DataInvalidException("Cart not found"));
        }
        return cartRepository.save(cart);
    }

    @Override
    public void addCartItem(List<String> books, String email) {
        CartEntity cart = getCart(email);
        books.forEach(id -> {
            CartItemEntity cartItem = cart.getCartItems().stream()
                    .filter(item -> Objects.equals(item.getBook().getId(), id))
                    .findFirst().orElse(null);
            BookEntity book = bookService.getBookEntityById(id);
            if(cartItem == null) {
                CartItemEntity cartItemEntity = CartItemEntity.builder()
                        .book(book)
                        .quantity(1L)
                        .unitPrice(book.getPrice())
                        .totalPrice(book.getPrice())
                        .build();
                cartItemRepository.save(cartItemEntity);
                cart.addItems(cartItemEntity);
            } else {
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemRepository.save(cartItem);
                cart.addItems(cartItem);
            }
            cartRepository.save(cart);
        });

    }

    @Override
    public void removeCartItem(String email, List<Long> cartItemIds) {
        CartEntity cart = getCart(email);
        cartItemIds.forEach(cart::removeItems);
        cartRepository.save(cart);
    }

    public void updateItemQuantity(String email, Long cartItemId, Long quantity) {
        CartEntity cart = getCart(email);
        cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .ifPresentOrElse((item) -> {
                    item.setQuantity(quantity);
                }, () -> {
                    throw new DataInvalidException("Cart item not found");
                });
        cartRepository.save(cart);
    }
}
