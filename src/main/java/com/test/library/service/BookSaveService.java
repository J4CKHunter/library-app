package com.test.library.service;

import com.test.library.dto.BookListItemResponse;
import com.test.library.dto.SaveBookRequest;
import com.test.library.model.Book;
import com.test.library.model.Category;
import com.test.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookSaveService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    // kaydetme sırasında sıkıntı olursa rollback yapması için transactional yapıldı
    @Transactional
    public BookListItemResponse saveBook(SaveBookRequest saveBookRequest){

        Category category = categoryService.loadCategory(saveBookRequest.getCategoryId());

        final Book book = Book.builder()
                .category(category)
                .status(saveBookRequest.getStatus())
                .title(saveBookRequest.getTitle())
                .lastReadPageNumber(saveBookRequest.getLastReadPageNumber())
                .totalPage(saveBookRequest.getTotalPage())
                .author(saveBookRequest.getAuthor())
                .publisher(saveBookRequest.getPublisher())
                .build();

        final Book bookFromDb = bookRepository.save(book);

        return BookListItemResponse.builder()
                .categoryName(book.getCategory().getName())
                .id(bookFromDb.getId())
                .status(bookFromDb.getStatus())
                .publisher(bookFromDb.getPublisher())
                .author(bookFromDb.getAuthor())
                .lastReadPageNumber(bookFromDb.getLastReadPageNumber())
                .totalPage(bookFromDb.getTotalPage())
                .title(bookFromDb.getTitle())
                .build();
    }
}
