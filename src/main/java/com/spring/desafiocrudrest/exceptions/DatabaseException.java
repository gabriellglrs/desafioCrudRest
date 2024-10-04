package com.spring.desafiocrudrest.exceptions;

public class DatabaseException extends RuntimeException{

     public DatabaseException(String message) {
          super(message);
     }
}
