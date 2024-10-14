package com.qlBanSach.BookManager.Repository;

import com.qlBanSach.BookManager.Model.Entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {
    RoleEntity findByCode(String name);
}
