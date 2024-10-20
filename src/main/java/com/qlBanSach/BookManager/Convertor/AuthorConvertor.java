package com.qlBanSach.BookManager.Convertor;

import com.qlBanSach.BookManager.Model.DTO.AuthorDTO;
import com.qlBanSach.BookManager.Model.Entity.AuthorEntity;
import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Response.AuthorResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthorConvertor {
    private final ModelMapper modelMapper;

    public AuthorEntity dtoToEntity(AuthorDTO dto) {
        AuthorEntity authorEntity = modelMapper.map(dto, AuthorEntity.class);
        return authorEntity;
    }

    public AuthorResponse entityToResponse(AuthorEntity authorEntity) {
        AuthorResponse authorResponse = modelMapper.map(authorEntity, AuthorResponse.class);
        if (authorEntity.getBooks() != null) {
            List<String> bookNames = authorEntity.getBooks()
                    .stream()
                    .map(BookEntity::getName)
                    .toList();
            authorResponse.setBooks(bookNames);
        }
        return authorResponse;
    }
}
