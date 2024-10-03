package com.spring.desafiocrudrest.services;

import com.spring.desafiocrudrest.dtos.ClientDto;
import com.spring.desafiocrudrest.entities.ClientEntity;
import com.spring.desafiocrudrest.repositories.ClientRepository;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ClientDto insert(ClientDto dto) {
        ClientEntity entity = new ClientEntity();
        copyToEntityDto(entity, dto);
        entity = repository.save(entity);
        return new ClientDto(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<ClientEntity> clientes = repository.findAll(pageable);
        return clientes.map(ClientDto::new);
    }

    private void copyToEntityDto(ClientEntity entity, ClientDto dto) {
        entity.setId(dto.id());
        entity.setName(dto.name());
        entity.setCpf(dto.cpf());
        entity.setIncome(dto.income());
        entity.setLocalDate(dto.localDate());
        entity.setChildren(dto.children());
    }
}
