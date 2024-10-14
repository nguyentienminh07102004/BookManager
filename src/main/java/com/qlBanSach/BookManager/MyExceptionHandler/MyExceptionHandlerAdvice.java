package com.qlBanSach.BookManager.MyExceptionHandler;

import com.qlBanSach.BookManager.Model.Response.APIResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandlerAdvice {

    @ExceptionHandler(value = DataInvalidException.class)
    public APIResponse DataInvalidExceptionHandler(DataInvalidException exception) {
        return APIResponse.builder()
                .message(exception.getMessage())
                .response(exception)
                .build();
    }
}
