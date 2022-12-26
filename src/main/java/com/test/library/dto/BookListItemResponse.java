package com.test.library.dto;

import com.test.library.model.BookStatus;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.io.File;

@Data
@SuperBuilder
public class BookListItemResponse {
    // book kaydedildikten sonra detay sayfasına atılacağı yerde çağrılacak
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private Integer lastReadPageNumber;
    private BookStatus status;
    private File file;
    private String categoryName;
    private Integer totalPage;
}
