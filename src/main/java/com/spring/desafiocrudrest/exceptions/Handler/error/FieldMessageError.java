package com.spring.desafiocrudrest.exceptions.Handler.error;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldMessageError {
     private String fieldName;
     private String errorMessage;
}
