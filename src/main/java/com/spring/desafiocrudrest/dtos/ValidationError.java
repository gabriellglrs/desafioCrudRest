package com.spring.desafiocrudrest.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
public class ValidationError extends CustomError {

     @Builder.Default
     private List<FieldMessage> errors = new ArrayList<>();

     public void addError(String fieldName, String message) {
          errors.add(new FieldMessage(fieldName, message));
     }
}
