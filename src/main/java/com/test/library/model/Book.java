package com.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


/*
*
*            --> OneToMany -->
* category                       book
*            <-- ManyToOne <--
*
* */

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book extends BaseEntity{

    private String title;
    private String author;
    private String publisher;
    private Integer lastReadPageNumber;
    private Integer totalPage;
    @Enumerated(EnumType.STRING)
    private BookStatus status;
    private Long userId;

//    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Image.class)
    @OneToOne
    private Image image;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "", nullable = false)
    private Category category;
}
