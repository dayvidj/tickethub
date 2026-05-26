package com.dayvid.ticketeira.entity;

import com.dayvid.ticketeira.dto.EventoRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "eventos")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private LocalDateTime data;
    private String local;
    private String descricao;

    public Evento(EventoRequestDTO eventoRequestDTO) {
        this.nome = eventoRequestDTO.nome();
        this.data = eventoRequestDTO.dataHora();
        this.local = eventoRequestDTO.local();
        this.descricao = eventoRequestDTO.descricao();
    }
}
