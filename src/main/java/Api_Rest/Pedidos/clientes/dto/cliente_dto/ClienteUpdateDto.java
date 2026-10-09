package Api_Rest.Pedidos.clientes.dto.cliente_dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteUpdateDto {

    private String name;

    private String tiposPagamentoPadrao;
}
