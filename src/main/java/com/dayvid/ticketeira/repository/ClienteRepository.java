package com.dayvid.ticketeira.repository;

import com.dayvid.ticketeira.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
