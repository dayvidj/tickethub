package com.dayvid.ticketeira.repository;

import com.dayvid.ticketeira.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {
}
