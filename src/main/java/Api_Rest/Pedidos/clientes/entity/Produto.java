package Api_Rest.Pedidos.clientes.entity;

import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
        this.nome = dto.nome();
        this.preco = dto.preco();
        this.descricao = dto.descricao();
        this.statusPedido = dto.statusPedido();
    }
}
