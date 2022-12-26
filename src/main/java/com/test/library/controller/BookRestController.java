package com.test.library.controller;

import com.test.library.dto.BookListItemResponse;
import com.test.library.dto.BookResponse;
import com.test.library.dto.SaveBookRequest;
import com.test.library.model.Book;
import com.test.library.model.BookStatus;
import com.test.library.model.CategoryType;
import com.test.library.service.BookListService;
import com.test.library.service.BookSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookRestController {

    private final BookListService bookListService;
    private final BookSaveService bookSaveService;

    @PostMapping("/save")
    public ResponseEntity<BookListItemResponse> saveBook(@Valid @RequestBody SaveBookRequest request){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(bookSaveService.saveBook(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookResponse>> listBook(@RequestParam(name = "size") int size,
                                               @RequestParam(name = "page") int page){

        return ResponseEntity.ok(bookListService.listBooks(size, page));
    }

    @GetMapping("/list/{categoryType}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable CategoryType categoryType){
        return ResponseEntity.ok(bookListService.searchByCategory(categoryType));
    }

    @GetMapping("/list/{status}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable BookStatus status){
        return ResponseEntity.ok(bookListService.searchBookStatus(status));
    }

    @GetMapping("/list/{title}")
    public ResponseEntity<List<BookResponse>> listByCategory(@PathVariable String title){
        return ResponseEntity.ok(bookListService.searchByTitle(title));
    }

}
