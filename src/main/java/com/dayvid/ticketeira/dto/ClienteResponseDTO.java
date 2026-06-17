package com.dayvid.ticketeira.dto;

import com.dayvid.ticketeira.entity.Cliente;

public record ClienteResponseDTO(Long id, String nome, String email, String telefone, String cpf) {

    public ClienteResponseDTO(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getTelefone(), cliente.getCpf());
    }

}
