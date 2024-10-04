package com.spring.desafiocrudrest.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Getter
@Setter
@SuperBuilder
public class CustomError {
     private Instant timestamp;
     private Integer status;
     private String error;
     private String path;
}
