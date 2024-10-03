package com.spring.desafiocrudrest.dtos;

import com.spring.desafiocrudrest.entities.ClientEntity;

import java.time.LocalDate;

public record ClientDto(Long id, String name, String cpf, Double income, LocalDate localDate, Integer children) {

    public ClientDto(ClientEntity entity) {
        this(entity.getId(), entity.getName(), entity.getCpf(), entity.getIncome(), entity.getLocalDate(), entity.getChildren());
    }
}
