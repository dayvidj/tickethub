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
        var cliente = this.buscarClientePorId(id);
        return new ClienteResponseDTO(cliente);
    }

    @Transactional
    public ClienteResponseDTO atualizarCliente(Long id, ClienteRequestDTO dadosCliente) {
        var cliente = this.buscarClientePorId(id);

        this.validarEmailECpf(dadosCliente.email(), dadosCliente.cpf(), id);

        cliente.atualizarDados(dadosCliente);

        return new ClienteResponseDTO(cliente);
    }


    // Método privado para validar se o email e CPF já estão cadastrados no banco de dados
    private void validarEmailECpf(String email, String cpf) {
        if(clienteRepository.existsByEmail(email)) {
            throw new ClienteJaCadastradoException("Email já cadastrado");
        }
        if(clienteRepository.existsByCpf(cpf)) {
            throw new ClienteJaCadastradoException("CPF já cadastrado");
        }
    }

    // Sobrecarga do método validarEmailECpf para atualização de cliente ignorando os campos no próprio cliente
    private void validarEmailECpf(String email, String cpf, Long id) {
        if (clienteRepository.existsByEmailAndIdNot(email, id)) {
            throw new ClienteJaCadastradoException("Email já cadastrado");
        }
        if (clienteRepository.existsByCpfAndIdNot(cpf, id)) {
            throw new ClienteJaCadastradoException("CPF já cadastrado");
        }
    }

    // Método privado para buscar um cliente por ID, lançando uma exceção se não encontrado
    private Cliente buscarClientePorId(Long id) {
            return clienteRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Cliente não encontrado"));
    }
}
