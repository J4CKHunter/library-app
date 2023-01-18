package com.test.library.dto;

import com.test.library.model.Role;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String username;
    private Role role;
    private String email;
}
