package com.dayvid.ticketeira.repository;

import com.dayvid.ticketeira.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {
}
