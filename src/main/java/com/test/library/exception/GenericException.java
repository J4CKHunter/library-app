package com.test.library.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
public class GenericException extends RuntimeException{

    private HttpStatus httpStatus;
    private ErrorCode errorCode;
    private String errorMessage;
}
