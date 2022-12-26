package com.test.library.service;

import com.test.library.dto.BookResponse;
import com.test.library.dto.BookSearchRequest;
import com.test.library.model.Book;
import com.test.library.model.BookStatus;
import com.test.library.model.Category;
import com.test.library.model.CategoryType;
import com.test.library.repository.BookRepository;
import com.test.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookListService {
    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    // convert request param
    public List<BookResponse> listBooks(int size, int page){
        return bookRepository.findAll(PageRequest.of(page, size))
                    .getContent()
                    .stream()
                    .map( BookListService::convertResponse)
                    .collect(Collectors.toList());

    }

    public List<BookResponse> searchByCategory(CategoryType categoryType){
        final Category category = categoryService.findByName(categoryType.getValue());
        return category.getBooks().stream()
                .map(BookListService::convertResponse)
                .collect(Collectors.toList());
    }

    private static BookResponse convertResponse(Book model) {
        return BookResponse.builder()
                .author(model.getAuthor())
                .title(model.getTitle())
                .imageUrl(model.getImage().getImageUrl())
                .build();
    }

    public List<BookResponse> searchBookStatus(BookStatus bookStatus){
        return bookRepository.findByBookStatus(bookStatus).stream()
                .map(each ->
                    BookResponse.builder()
                            .id(each.getId())
                            .imageUrl(each.getImage().getImageUrl())
                            .build()
                )
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchByTitle(String title){
        return bookRepository.findByTitle(title).stream()
                .map(each ->
                        BookResponse.builder()
                                .id(each.getId())
                                .imageUrl(each.getImage().getImageUrl())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
