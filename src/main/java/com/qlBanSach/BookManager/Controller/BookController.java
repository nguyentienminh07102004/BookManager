package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.Service.Book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/books")
public class BookController {
    private final IBookService bookService;

    @PostMapping(value = "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse create(@RequestBody BookDTO bookDTO) {
        BookResponse bookResponse = bookService.saveBook(bookDTO);
        return APIResponse.builder()
                .message("CREATE BOOK SUCCESS")
                .response(bookResponse)
                .build();
    }

    @PutMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse update(@RequestBody BookDTO bookDTO) {
        BookResponse bookResponse = bookService.saveBook(bookDTO);
        return APIResponse.builder()
                .message("UPDATE BOOK SUCCESS")
                .response(bookResponse)
                .build();
    }

    @DeleteMapping(value = "/{ids}")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        bookService.deleteBookByIds(ids);
        return APIResponse.builder()
                .message("DELETE SUCCESS")
                .build();
    }
}
