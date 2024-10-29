package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    void deleteByUser_Email(String email);
}
