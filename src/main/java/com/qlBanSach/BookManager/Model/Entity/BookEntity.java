package com.qlBanSach.BookManager.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
public class BookEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private Double price;
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;
    @Column(name = "quantity")
    private Long quantity;
    @Column(name = "thumbnail", columnDefinition = "LONGTEXT")
    private String thumbnail;
    @ManyToMany()
    @JoinTable(name = "author_book",
    joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "authorId", referencedColumnName = "id"))
    private List<AuthorEntity> authors;
}
