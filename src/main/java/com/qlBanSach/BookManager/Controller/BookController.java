package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.Service.Author.AuthorService;
import com.qlBanSach.BookManager.Service.Book.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/books")
public class BookController {
    private final IBookService bookService;
    private final AuthorService authorService;
    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

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

    @GetMapping(value = "/{id}/update")
    public ModelAndView list(@PathVariable String id) {
        ModelAndView view = new ModelAndView("admin/books/edit");
        view.addObject("authors", authorService.getAllAuthorsByBookId(id).stream()
                .map(AuthorResponse::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
        view.addObject("book", bookService.getBookById(id));
        return view;
    }

    @DeleteMapping(value = "/{ids}")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        bookService.deleteBookByIds(ids);
        return APIResponse.builder()
                .message("DELETE SUCCESS")
                .build();
    }

    @GetMapping(value = "/{id}")
    public ModelAndView getBook(@PathVariable String id) {
        ModelAndView view = new ModelAndView("admin/books/detail");
        view.addObject("book", bookService.getBookById(id));
        return view;
    }

    @GetMapping(value = "/add")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView addBooks() {
        return new ModelAndView("admin/books/add");
    }


    @GetMapping(value = "/list")
    public ModelAndView getBooks() {
        ModelAndView view = new ModelAndView("admin/books/list");
        view.addObject("books", bookService.getAllBooks());
        return view;
    }
}
