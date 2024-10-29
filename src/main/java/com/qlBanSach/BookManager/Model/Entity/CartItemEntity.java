package com.qlBanSach.BookManager.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "cartItem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bookId")
    private BookEntity book;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "unitPrice")
    private Double unitPrice;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @ManyToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private CartEntity cart;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderEntity order;
}
