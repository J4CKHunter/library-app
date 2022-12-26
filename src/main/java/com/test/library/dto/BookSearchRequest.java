package com.test.library.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class BookSearchRequest {
    private int size;
    private int page;
}
