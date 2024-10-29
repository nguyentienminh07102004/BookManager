package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.CartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartItemRepository extends JpaRepository<CartItemEntity, Long> {
    void deleteAllByCart_Id(Long id);
}
