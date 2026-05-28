package com.dayvid.ticketeira.service;

import com.dayvid.ticketeira.dto.EventoRequestDTO;
import com.dayvid.ticketeira.dto.EventoResponseDTO;
import com.dayvid.ticketeira.entity.Evento;
import com.dayvid.ticketeira.exception.RecursoNaoEncontradoException;
import com.dayvid.ticketeira.repository.EventoRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Transactional(readOnly = true)
    public List<EventoResponseDTO> listarEventos() {
        var eventos = eventoRepository.findAll();
        var listaEventos = eventos.stream()
                .map(EventoResponseDTO::new) // Converte cada entidade para DTO
                .toList();

        return listaEventos;
    }

    @Transactional(readOnly = true)
    public EventoResponseDTO buscarEventoPorId(Long id) {
        var evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Evento não encontrado"));

        return new EventoResponseDTO(evento);
    }
}
