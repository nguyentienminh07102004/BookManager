package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Long> {
}
