package com.qlBanSach.BookManager.Model.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<CartItemEntity> cartItems;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
}
