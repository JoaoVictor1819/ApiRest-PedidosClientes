package Api_Rest.Pedidos.clientes.dto.endereco_dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EnderecoUpdateDto {

    private String cep;

    private String estado;

    private String cidade;

    private Integer numeroResidencia;

}
