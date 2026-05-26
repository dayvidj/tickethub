package com.dayvid.ticketeira.controller;

import com.dayvid.ticketeira.dto.EventoRequestDTO;
import com.dayvid.ticketeira.dto.EventoResponseDTO;
import com.dayvid.ticketeira.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoResponseDTO> salvarEvento(@RequestBody @Valid EventoRequestDTO dadosEvento) {
        var eventoCriado = eventoService.criarEvento(dadosEvento);
        return ResponseEntity.status(201).body(eventoCriado);
    }
}
