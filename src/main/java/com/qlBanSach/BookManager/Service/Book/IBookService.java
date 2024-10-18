package com.qlBanSach.BookManager.Service.Book;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Response.BookResponse;

import java.util.List;

public interface IBookService {
    BookResponse saveBook(BookDTO book);
    void deleteBookByIds(List<String> ids);
}
