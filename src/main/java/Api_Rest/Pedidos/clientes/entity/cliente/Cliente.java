package Api_Rest.Pedidos.clientes.entity.cliente;


import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteRequestDto;
import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private TiposPamentos tiposPamentos;

    @OneToMany(mappedBy = "cliente")
    private Set<Produto> produtos = new HashSet<>();

    public Cliente(ClienteRequestDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.tiposPamentos = dto.getTiposPamentoPadrao();
    }
}
