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

    public List<AuthorResponse> getAllAuthorsByName(String name) {
        List<AuthorEntity> authorEntities = authorRepository.findByNameStartingWith(name);
        List<AuthorResponse> authorResponses = new ArrayList<>();
        if (!authorEntities.isEmpty()) {
            authorEntities.forEach(authorEntity -> {
                AuthorResponse authorResponse = authorConvertor.entityToResponse(authorEntity);
                authorResponses.add(authorResponse);
            });
        }
        return authorResponses;
    }

    public AuthorEntity getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElseThrow(() -> new DataInvalidException("Author Not Found!!!"));
    }
}
