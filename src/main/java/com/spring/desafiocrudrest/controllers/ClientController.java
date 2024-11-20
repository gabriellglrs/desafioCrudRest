package com.spring.desafiocrudrest.controllers;

import com.spring.desafiocrudrest.DTO.ClientDto;
import com.spring.desafiocrudrest.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto) {
        ClientDto novoDto = service.insert(dto);
        return ResponseEntity.ok(novoDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id) {
        ClientDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
        Page<ClientDto> clientDtos = service.findAll(pageable);
        return ResponseEntity.ok(clientDtos);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id,@Valid @RequestBody ClientDto dto) {
        ClientDto novo = service.update(id,dto);
        return ResponseEntity.ok(novo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
         service.delete(id);
         return ResponseEntity.noContent().build();
    }

}
