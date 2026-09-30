package Api_Rest.Pedidos.clientes.dto.cliente_dto;

import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoResponseDto;
import Api_Rest.Pedidos.clientes.entity.Endereco;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteResponseDto {

    Long id;

    private String name;

    private String email;

    private TiposPamentos tiposPamentoPadrao;

    private List<ProdutoResponseDto> produtosResumoDtos;

    private Endereco endereco;
}
