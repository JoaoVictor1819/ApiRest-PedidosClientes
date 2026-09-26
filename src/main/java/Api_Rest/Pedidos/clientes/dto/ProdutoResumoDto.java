package Api_Rest.Pedidos.clientes.dto;


import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProdutoResumoDto {

    private Long id;

    private String nome;

    private String descricao;

    private BigDecimal preco;

    private StatusPedido statusPedido;
}
