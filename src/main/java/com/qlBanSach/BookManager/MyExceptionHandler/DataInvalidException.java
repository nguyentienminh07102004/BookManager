package com.qlBanSach.BookManager.MyExceptionHandler;

public class DataInvalidException extends RuntimeException {
    public DataInvalidException(String message) {
        super(message == null ? "Data input is invalid!" : message);
    }

    public DataInvalidException() {
        super("Data input is invalid!");
    }
}
