package com.test.library.service;

import com.test.library.model.Category;
import com.test.library.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category loadCategory(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name).orElseThrow(RuntimeException::new);
    }
}
