package com.dayvid.ticketeira.service;

import com.dayvid.ticketeira.dto.EventoRequestDTO;
import com.dayvid.ticketeira.dto.EventoResponseDTO;
import com.dayvid.ticketeira.entity.Evento;
import com.dayvid.ticketeira.repository.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Transactional
    public EventoResponseDTO criarEvento(EventoRequestDTO eventoRequestDTO) {
        var evento = new Evento(eventoRequestDTO); // Converter DTO para entidade
        eventoRepository.save(evento);
        return new EventoResponseDTO(evento);
    }

}
