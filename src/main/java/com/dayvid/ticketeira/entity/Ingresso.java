package com.dayvid.ticketeira.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "ingressos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;

    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    private TipoIngresso tipo;

    @Enumerated(EnumType.STRING)
    private StatusIngresso status;

}
