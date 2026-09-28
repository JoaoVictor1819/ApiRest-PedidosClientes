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

    @Column(unique = true, nullable = true)
    private String email;

    private TiposPamentos tiposPamentos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //Classe nao proprietaria| Relacionamento de um para muintos que sera consumido pela entidade que leva @ManyToOne
    private Set<Produto> produtos = new HashSet<>();

    public Cliente(ClienteRequestDto dto) {
        this.name = dto.getName();
        this.email = dto.getEmail();
        this.tiposPamentos = dto.getTiposPamentoPadrao();
    }
}
