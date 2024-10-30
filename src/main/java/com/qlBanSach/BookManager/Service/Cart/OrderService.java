package com.qlBanSach.BookManager.Service.Cart;

import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import com.qlBanSach.BookManager.Model.Entity.CartItemEntity;
import com.qlBanSach.BookManager.Model.Entity.OrderEntity;
import com.qlBanSach.BookManager.Repository.ICartRepository;
import com.qlBanSach.BookManager.Repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private final ICartService cartService;
    private final ICartRepository cartRepository;
    private final IOrderRepository orderRepository;
    @Override
    @Transactional
    public OrderEntity createOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        CartEntity cart = cartService.getCart(email);
        List<CartItemEntity> cartItemEntities = cart.getCartItems();
        OrderEntity order = OrderEntity.builder()
                .status("ACCEPT")
                .cartItems(cartItemEntities)
                .totalPrice(cart.getTotalAmount())
                .build();
        cartItemEntities.forEach(item -> item.setOrder(order));
        cart.getCartItems().forEach(item -> {
            item.setCart(null);
        });
        cart.setCartItems(new ArrayList<>());
        cart.setTotalAmount(0.0D);
        cartRepository.save(cart);
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        orderRepository.deleteByUser_Email(email);
    }
}
