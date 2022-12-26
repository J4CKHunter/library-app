package com.test.library.repository;

import com.test.library.model.Book;
import com.test.library.model.Category;
import com.test.library.model.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Book> {
    Optional<Category> findByName(String name);
}
