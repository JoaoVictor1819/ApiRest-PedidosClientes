package Api_Rest.Pedidos.clientes.dto.projection;

import Api_Rest.Pedidos.clientes.entity.cliente.TiposPagamentos;

public interface ClienteProjection {

    Long getId();
    String getName();
    String getEmail();
    TiposPagamentos getTiposPagamentos();
    Long getEnderecoId();
    String getCidade();
    String getEstado();
    String getCep();
    Integer getNumero();


}
