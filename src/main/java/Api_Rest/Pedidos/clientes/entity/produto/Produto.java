package Api_Rest.Pedidos.clientes.entity.produto;

import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoUpdateDto;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
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

    @Column(name = "Nome_Produto", nullable = false, unique = true)
    private String nome;

    @Column(name = "Descricao_Produto", nullable = false)
    private String descricao;

    @Column(name = "Preco_Produto", nullable = false)
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;

    @ManyToOne(fetch = FetchType.LAZY) //Classe Proprietaria| Relacionamento de muintos para um,
    // metodo responsevel por consumir os dados da entidade que esta levando @OneToMany que mantem a chave estrangeira
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Produto(ProdutoUpdateDto dto) {
        this.nome = dto.getNome();
        this.preco = dto.getPreco();
        this.descricao = dto.getDescricao();
        this.statusPedido = dto.getStatusPedido();
    }
}
