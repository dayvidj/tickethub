package com.dayvid.ticketeira.controller;

import com.dayvid.ticketeira.dto.EventoRequestDTO;
import com.dayvid.ticketeira.dto.EventoResponseDTO;
import com.dayvid.ticketeira.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listarEventos() {
        var eventos = eventoService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> obterEventoPorId(@PathVariable Long id) {
        var evento = eventoService.buscarEventoPorId(id);
        return ResponseEntity.ok(evento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventoResponseDTO> atualizarEvento(@PathVariable Long id, @RequestBody EventoRequestDTO dadosEvento) {
        var eventoAtualizado = eventoService.atualizarEvento(id, dadosEvento);
        return ResponseEntity.ok(eventoAtualizado);
    }



}
