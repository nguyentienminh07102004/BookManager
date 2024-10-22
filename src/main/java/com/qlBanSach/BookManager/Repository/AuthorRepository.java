package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.AuthorEntity;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
    List<AuthorEntity> findByNameContaining(String name);
    List<AuthorEntity> findByBooks_Id(String id);
}
