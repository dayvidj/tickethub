package com.dayvid.ticketeira.entity;

import com.dayvid.ticketeira.dto.ClienteRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    public Cliente(ClienteRequestDTO dadosCliente) {
        this.nome = dadosCliente.nome();
        this.email = dadosCliente.email();
        this.telefone = dadosCliente.telefone();
        this.cpf = dadosCliente.cpf();
    }
}
