package com.qlBanSach.BookManager.Convertor;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Entity.AuthorEntity;
import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookConvertor {
    private final ModelMapper modelMapper;

    public BookEntity dtoToEntity(BookDTO bookDTO) {
        BookEntity bookEntity = modelMapper.map(bookDTO, BookEntity.class);
        return bookEntity;
    }

    public BookResponse entityToResponse(BookEntity book) {
        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        String authors = String.join(", ", book.getAuthors().stream().map(AuthorEntity::getName).toList());
        bookResponse.setAuthors(authors);
        return bookResponse;
    }
}
