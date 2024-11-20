package com.spring.desafiocrudrest.services;

import com.spring.desafiocrudrest.DTO.ClientDto;
import com.spring.desafiocrudrest.entities.ClientEntity;
import com.spring.desafiocrudrest.exceptions.DatabaseException;
import com.spring.desafiocrudrest.exceptions.ResourceNotFoundException;
import com.spring.desafiocrudrest.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            ClientEntity entity = new ClientEntity();
            copyToEntityDto(entity, dto);
            entity = repository.save(entity);
            return new ClientDto(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de violacao de integridade");
        }
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id) {
        ClientEntity clientes = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException( "id: " + id + " Produto nao encontrado"));
       return new ClientDto(clientes);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable) {
        Page<ClientEntity> clientes = repository.findAll(pageable);
        return clientes.map(ClientDto::new);
    }

    @Transactional()
    public ClientDto update(Long id, ClientDto dto) {
        try {
            ClientEntity entity = repository.getReferenceById(id);
            copyToEntityDto(entity, dto);
            entity = repository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("id: " + id + " Produto nao encontrado");
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de violacao de integridade");
        }
    }

    @Transactional()
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("id: " + id + " Produto nao encontrado");
        }
        try {
            ClientEntity entity = repository.getReferenceById(id);
            repository.delete(entity);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de violacao de integridade");
        }
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
