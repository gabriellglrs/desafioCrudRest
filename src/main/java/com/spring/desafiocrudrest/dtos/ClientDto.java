package com.spring.desafiocrudrest.dtos;

import com.spring.desafiocrudrest.entities.ClientEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


import java.time.LocalDate;

public record ClientDto(
        Long id,

        @Size(min = 3, max = 80, message = "nome presisar ter de 3 a 80 caracteres")
        @NotBlank(message = "Campo requerido")
        String name,

        String cpf,
        Double income,
        LocalDate localDate,
        Integer children) {

    public ClientDto(ClientEntity entity) {
        this(entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getIncome(),
                entity.getLocalDate(),
                entity.getChildren());
    }
}
