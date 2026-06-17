package com.dayvid.ticketeira.service;

import com.dayvid.ticketeira.dto.ClienteRequestDTO;
import com.dayvid.ticketeira.dto.ClienteResponseDTO;
import com.dayvid.ticketeira.entity.Cliente;
import com.dayvid.ticketeira.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dadosCliente) {
        var cliente = new Cliente(dadosCliente);
        clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

}
