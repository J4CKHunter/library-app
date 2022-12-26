package com.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image extends BaseEntity{

    private String imageUrl;

}
