package com.test.library.dto;

import com.test.library.model.BookStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@SuperBuilder
@NoArgsConstructor
public class BookResponse {
    //
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Integer lastReadPageNumber;
    private BookStatus status;
    private File file;
    private Long categoryId;
    private Integer totalPage;
    private String imageUrl;
}
