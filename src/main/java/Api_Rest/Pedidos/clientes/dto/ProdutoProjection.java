package Api_Rest.Pedidos.clientes.dto;

import Api_Rest.Pedidos.clientes.entity.produto.StatusPedido;

import java.math.BigDecimal;

public interface ProdutoProjection {

    Long getId();
    String getNome();
    String getDescricao();
    BigDecimal getPreco();
    StatusPedido getStatusPedido();
    Long getClienteId();
    String getClienteNome();
}
