package com.test.library.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {

    private String accessToken;

    // login olan user'a ait bilgileri getirmesi için
    private UserDto userDto;
}
