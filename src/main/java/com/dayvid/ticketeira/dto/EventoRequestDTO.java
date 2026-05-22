package com.dayvid.ticketeira.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventoRequestDTO(
        @NotBlank
        String nome,
        @NotBlank
        LocalDateTime data,
        @NotBlank
        String local,
        @NotBlank
        @Size(max = 255)
        String descricao) {
}
