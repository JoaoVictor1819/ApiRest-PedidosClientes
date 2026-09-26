package Api_Rest.Pedidos.clientes.dto;

import Api_Rest.Pedidos.clientes.entity.TiposPamentos;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteDto {

        private Long id;

        @NotBlank
        private String name;

        @NotBlank
        private String email;

        private TiposPamentos tiposPamentoPadrao;

        private List<ProdutoResumoDto> produtosResumoDtos;

}
