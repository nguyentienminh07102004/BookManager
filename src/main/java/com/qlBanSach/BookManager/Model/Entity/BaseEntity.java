package com.qlBanSach.BookManager.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
@Getter
@Setter
public class BaseEntity {
    @Column(name = "create_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "created_date")
    @CreatedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    @Temporal(value = TemporalType.TIMESTAMP)
    private LocalDateTime modifiedDate;
}
