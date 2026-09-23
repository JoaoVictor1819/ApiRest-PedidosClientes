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

    private String nome;

    private String descricao;

    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    StatusPedido statusPedido;


    public Produto(ProdutoDto dto) {
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
        this.statusPedido = dto.getStatusPedido();
    }
}
