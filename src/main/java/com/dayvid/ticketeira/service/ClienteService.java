package com.dayvid.ticketeira.service;

import com.dayvid.ticketeira.dto.ClienteRequestDTO;
import com.dayvid.ticketeira.dto.ClienteResponseDTO;
import com.dayvid.ticketeira.entity.Cliente;
import com.dayvid.ticketeira.exception.ClienteJaCadastradoException;
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

        this.validarEmailECpf(dadosCliente.email(), dadosCliente.cpf());

        var cliente = new Cliente(dadosCliente);
        clienteRepository.save(cliente);

        return new ClienteResponseDTO(cliente);
    }

    private void validarEmailECpf(String email, String cpf) {
        if(clienteRepository.existsByEmail(email)) {
            throw new ClienteJaCadastradoException("Email já cadastrado");
        }
        if(clienteRepository.existsByCpf(cpf)) {
            throw new ClienteJaCadastradoException("CPF já cadastrado");
        }
    }

}
