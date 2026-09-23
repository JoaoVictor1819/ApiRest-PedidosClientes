package Api_Rest.Pedidos.clientes.entity;

import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    StatusPedido statusPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    public Produto(ProdutoDto dto) {
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
        this.statusPedido = dto.getStatusPedido();
    }
}
