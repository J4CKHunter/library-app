package com.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@NoArgsConstructor
public class User extends BaseEntity{

}
