package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, String> {
    Page<BookEntity> findByNameContaining(String name, Pageable pageable);
}
