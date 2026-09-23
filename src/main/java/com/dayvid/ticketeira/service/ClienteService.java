package com.dayvid.ticketeira.service;

import com.dayvid.ticketeira.dto.ClienteRequestDTO;
import com.dayvid.ticketeira.dto.ClienteResponseDTO;
import com.dayvid.ticketeira.entity.Cliente;
import com.dayvid.ticketeira.exception.ClienteJaCadastradoException;
import com.dayvid.ticketeira.exception.RecursoNaoEncontradoException;
import com.dayvid.ticketeira.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listarClientes() {
        var clientes = clienteRepository.findAll().stream()
                .map(ClienteResponseDTO::new).collect(Collectors.toList());
        return clientes;
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO obterClientePorId(Long id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
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
