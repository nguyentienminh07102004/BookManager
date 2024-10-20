package com.qlBanSach.BookManager.Controller;

import com.qlBanSach.BookManager.Model.DTO.AuthorDTO;
import com.qlBanSach.BookManager.Model.Response.APIResponse;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import com.qlBanSach.BookManager.Service.Author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping()
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

    @GetMapping("/search")
    @ResponseStatus(value = HttpStatus.OK)
    public APIResponse search(@RequestParam String name) {
        List<AuthorResponse> authors = authorService.getAllAuthorsByName(name);
        String message = authors.isEmpty() ? "NOT FOUND" : "FOUND";
        return APIResponse.builder()
                .message(message)
                .response(authors)
                .build();
    }
}
