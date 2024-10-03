package com.spring.desafiocrudrest.controllers;

import com.spring.desafiocrudrest.dtos.ClientDto;
import com.spring.desafiocrudrest.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clientes")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@RequestBody ClientDto dto) {
        ClientDto novoDto = service.insert(dto);
        return ResponseEntity.ok(novoDto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable) {
        Page<ClientDto> clientDtos = service.findAll(pageable);
        return ResponseEntity.ok(clientDtos);
    }

}
