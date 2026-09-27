package Api_Rest.Pedidos.clientes.dto.cliente_dto;

import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteRequestDto {


        @NotBlank
        private String name;

        @NotBlank
        @Email
        private String email;

        private TiposPamentos tiposPamentoPadrao;


}
