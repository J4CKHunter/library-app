package com.test.library.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Column(unique=true)
    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;

}
