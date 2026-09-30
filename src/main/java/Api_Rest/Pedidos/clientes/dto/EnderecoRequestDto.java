package Api_Rest.Pedidos.clientes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EnderecoRequestDto {

    private String cep;

    private String estado;

    private String cidade;

    private Integer numeroResidencia;

    private Long clienteId;
}
