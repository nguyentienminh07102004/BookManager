package com.qlBanSach.BookManager.Model.Entity;

import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "totalAmount")
    private Double totalAmount = 0.0D;
    @OneToMany(mappedBy = "cart")
    @Cascade(value = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<CartItemEntity> cartItems = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private UserEntity user;

    public void addItems(CartItemEntity cartItem) {
        cartItem.setCart(this);
        this.cartItems.add(cartItem);
        updateTotalAmount();
    }

    public void removeItems(Long cartItemId) {
        CartItemEntity CartItem = this.cartItems.stream()
                .filter(cartItem -> cartItem.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() -> new DataInvalidException("Cart Item not found!!"));
        CartItem.setCart(null);
        this.cartItems.remove(CartItem);
        updateTotalAmount();
    }

    public void updateTotalAmount() {
        Double totalAmount = this.cartItems.stream()
                .map(CartItemEntity::getTotalPrice)
                .reduce(0.0D, Double::sum);
        this.setTotalAmount(totalAmount);
    }
}
