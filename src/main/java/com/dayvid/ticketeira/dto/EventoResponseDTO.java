package com.dayvid.ticketeira.dto;

import com.dayvid.ticketeira.entity.Evento;

public record EventoResponseDTO (Long id, String nome, String data, String local, String descricao) {

    public EventoResponseDTO(Evento evento) {
        this(evento.getId(), evento.getNome(), evento.getData().toString(), evento.getLocal(), evento.getDescricao());
    }
}
