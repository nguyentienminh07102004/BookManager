package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Convertor.AuthorConvertor;
import com.qlBanSach.BookManager.Model.DTO.AuthorDTO;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import com.qlBanSach.BookManager.Service.Author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/authors")
public class AuthorController {
    private final AuthorService authorService;
    private final AuthorConvertor authorConvertor;

    @PostMapping(value = "/add")
    @ResponseStatus(value = HttpStatus.CREATED)
    public APIResponse create(@RequestBody AuthorDTO authorDTO) {
        AuthorResponse response = authorService.saveAuthor(authorDTO);
        return APIResponse.builder()
                .message("CREATE AUTHOR SUCCESS")
                .response(response)
                .build();
    }

    @PutMapping()
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse update(@RequestBody AuthorDTO authorDTO) {
        AuthorResponse response = authorService.saveAuthor(authorDTO);
        return APIResponse.builder()
                .message("UPDATE AUTHOR SUCCESS")
                .response(response)
                .build();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return APIResponse.builder()
                .message("DELETE SUCCESS")
                .build();
    }

    @GetMapping(value = "/{id}/update")
    public ModelAndView list(@PathVariable Long id) {
        ModelAndView view = new ModelAndView("admin/authors/edit");
        view.addObject("author", authorService.getAuthorById(id));
        return view;
    }

    @GetMapping("/list")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView search(@RequestParam(required = false) String name) {
        List<AuthorResponse> authors = authorService.getAllAuthorsByName(name);
        ModelAndView view = new ModelAndView("admin/authors/list");
        view.addObject("authors", authors);
        return view;
    }

    @GetMapping("/add")
    public ModelAndView addAuthors() {
        return new ModelAndView("admin/authors/add");
    }

    @GetMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ModelAndView getAuthor(@PathVariable Long id) {
        AuthorResponse author = authorConvertor.entityToResponse(authorService.getAuthorById(id));
        ModelAndView view = new ModelAndView("admin/authors/detail");
        view.addObject("author", author);
        return view;
    }
}
