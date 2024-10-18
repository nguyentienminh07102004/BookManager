package com.qlBanSach.BookManager.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
    @ManyToMany
    @JoinTable(name = "author_book",
    joinColumns = @JoinColumn(name = "bookId", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "authorId", referencedColumnName = "id"))
    private List<AuthorEntity> authors;
}
