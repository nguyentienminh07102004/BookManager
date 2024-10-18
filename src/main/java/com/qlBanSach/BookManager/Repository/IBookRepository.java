package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookRepository extends JpaRepository<BookEntity, String> {
}
