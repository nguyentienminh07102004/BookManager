package com.qlBanSach.BookManager.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private String id;
    private String name;
    private String description;
    private Double price;
    private List<Long> authors;
    private Long quantity;
}
