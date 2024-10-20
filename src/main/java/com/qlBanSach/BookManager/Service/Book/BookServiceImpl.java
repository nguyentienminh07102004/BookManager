package com.qlBanSach.BookManager.Service.Book;

import com.qlBanSach.BookManager.Convertor.BookConvertor;
import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.IBookRepository;
import com.qlBanSach.BookManager.Service.Author.AuthorService;
import com.qlBanSach.BookManager.Utils.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookConvertor bookConvertor;
    private final IBookRepository bookRepository;
    private final AuthorService authorService;
    private final Pagination pagination;

    @Override
    public BookResponse saveBook(BookDTO book) {
        if(book.getId() != null) {
            bookRepository.findById(book.getId())
                    .orElseThrow(() -> new DataInvalidException("Book not found!!!"));
        }
        BookEntity bookEntity = bookConvertor.dtoToEntity(book);

        bookEntity.setAuthors(new ArrayList<>());
        book.getAuthorIds().forEach(id -> {
            bookEntity.getAuthors().add(authorService.getAuthorById(id));
        });

        BookEntity response = bookRepository.save(bookEntity);
        return bookConvertor.entityToResponse(response);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        ids.forEach(id -> bookRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Book not found!!!")));
        bookRepository.deleteAllById(ids);
    }

    @Override
    public Page<BookResponse> getAllBooks(Integer page, Integer limit) {
        Pageable pageable = pagination.pageUtil(page, limit);
        Page<BookEntity> bookEntities = bookRepository.findAll(pageable);
        return bookEntities.map(bookConvertor::entityToResponse);
    }
}
