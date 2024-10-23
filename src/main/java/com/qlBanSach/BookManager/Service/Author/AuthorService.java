package com.qlBanSach.BookManager.Service.Author;

import com.qlBanSach.BookManager.Convertor.AuthorConvertor;
import com.qlBanSach.BookManager.Model.DTO.AuthorDTO;
import com.qlBanSach.BookManager.Model.Entity.AuthorEntity;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorConvertor authorConvertor;

    public AuthorResponse saveAuthor(AuthorDTO author) {
        AuthorEntity authorEntity;
        if (author.getId() != null) {
            authorEntity = authorRepository.findById(author.getId()).orElseThrow(() -> new DataInvalidException("Author Not Found!!!"));
            authorEntity.setName(author.getName());
            authorEntity.setDescription(author.getDescription());
        } else {
            authorEntity = authorConvertor.dtoToEntity(author);
        }
        AuthorEntity response = authorRepository.save(authorEntity);
        return authorConvertor.entityToResponse(response);
    }

    public void deleteAuthor(Long authorId) {
        AuthorEntity author = authorRepository.findById(authorId)
                .orElseThrow(() -> new DataInvalidException("Author Not Found!!!"));
        authorRepository.delete(author);
    }

    public List<AuthorResponse> getAllAuthorsByBookId(String bookId) {
        return authorRepository.findByBooks_Id(bookId).stream()
                .map(authorConvertor::entityToResponse)
                .toList();
    }

    public List<AuthorResponse> getAllAuthorsByName(String name) {
        List<AuthorEntity> authorResponses = new ArrayList<>();
        if(name == null) {
            authorResponses = authorRepository.findAll();
        } else {
            authorResponses = authorRepository.findByNameContaining(name);
        }
        return authorResponses.stream().map(authorConvertor::entityToResponse).toList();
    }

    public AuthorEntity getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new DataInvalidException("Author Not Found!!!"));
    }

    public AuthorEntity getOrCreateAuthor(String name) {
        return authorRepository.findByName(name).orElseGet(() -> {
            AuthorEntity authorEntity = new AuthorEntity();
            authorEntity.setName(name);
            return authorRepository.save(authorEntity);
        });
    }
}
