package com.qlBanSach.BookManager.Service.Book;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBookService {
    BookResponse saveBook(BookDTO book);
    void deleteBookByIds(List<String> ids);
    Page<BookResponse> getAllBooks(Integer page, Integer limit);
}
