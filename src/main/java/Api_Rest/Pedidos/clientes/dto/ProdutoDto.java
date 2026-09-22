package Api_Rest.Pedidos.clientes.dto;

import Api_Rest.Pedidos.clientes.entity.StatusPedido;

import java.math.BigDecimal;

public record ProdutoDto(
         String nome,

         String descricao,

         BigDecimal preco,

         StatusPedido statusPedido) {
}
