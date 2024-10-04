package com.spring.desafiocrudrest.dtos;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FieldMessage {
     private String fieldName;
     private String errorMessage;
}
