package com.qlBanSach.BookManager.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponse {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String authors;
    private Long quantity;
}
