package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorRepository extends JpaRepository<AuthorEntity, String> {
}
