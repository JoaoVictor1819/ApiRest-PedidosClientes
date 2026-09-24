package Api_Rest.Pedidos.clientes.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "metodos_pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MetodosPagamentos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private TiposPamentos tipoPagamento;
}
