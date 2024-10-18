package com.qlBanSach.BookManager.Service.Book;

import com.qlBanSach.BookManager.Convertor.BookConvertor;
import com.qlBanSach.BookManager.Model.DTO.BookDTO;
import com.qlBanSach.BookManager.Model.Entity.BookEntity;
import com.qlBanSach.BookManager.Model.Response.BookResponse;
import com.qlBanSach.BookManager.MyExceptionHandler.DataInvalidException;
import com.qlBanSach.BookManager.Repository.IBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements IBookService {
    private final BookConvertor bookConvertor;
    private final IBookRepository bookRepository;
    @Override
    public BookResponse saveBook(BookDTO book) {
        if(book.getId() != null) {
            bookRepository.findById(book.getId())
                    .orElseThrow(() -> new DataInvalidException("Book not found!!!"));
        }
        BookEntity bookEntity = bookConvertor.dtoToEntity(book);
        BookEntity response = bookRepository.save(bookEntity);
        return bookConvertor.entityToResponse(response);
    }

    @Override
    public void deleteBookByIds(List<String> ids) {
        ids.forEach(id -> bookRepository.findById(id)
                .orElseThrow(() -> new DataInvalidException("Book not found!!!")));
        bookRepository.deleteAllById(ids);
    }
}
