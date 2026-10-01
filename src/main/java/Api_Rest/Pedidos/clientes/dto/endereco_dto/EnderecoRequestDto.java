package Api_Rest.Pedidos.clientes.dto.endereco_dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EnderecoRequestDto {


    @NotBlank(message = "The street name is mandatory.")
    private String cep;

    @NotBlank(message = "The states name is mandatory.")
    private String estado;

    @NotBlank(message = "The city name is mandatory.")
    private String cidade;

    @NotNull(message = "The residence number is mandatory.")
    private Integer numeroResidencia;

    @NotNull(message = "The client ID is mandatory.")
    private Long clienteId;
}
