package com.test.library.dto;

import com.test.library.model.BookStatus;
import com.test.library.model.Category;
import com.test.library.model.Image;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

@Data
public final class SaveBookRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    @NotNull
    private BookStatus status;

    @NotBlank
    private String publisher;

    @NotNull
    private Integer lastReadPageNumber;


    private File file;

    @NotNull
    private Long categoryId;

    @NotNull
    private Integer totalPage;

}
