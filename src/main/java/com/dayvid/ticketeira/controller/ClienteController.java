package com.dayvid.ticketeira.controller;

import com.dayvid.ticketeira.dto.ClienteRequestDTO;
import com.dayvid.ticketeira.dto.ClienteResponseDTO;
import com.dayvid.ticketeira.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@RequestBody @Valid ClienteRequestDTO dadosCliente) {
        var cliente = clienteService.criarCliente(dadosCliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> listarClientes() {
        var clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> obterClientePorId(@PathVariable Long id) {
        var cliente = clienteService.obterClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

}
