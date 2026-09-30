package Api_Rest.Pedidos.clientes.dto.cliente_dto;

import Api_Rest.Pedidos.clientes.entity.Endereco;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteRequestDto {


        @NotBlank(message = "The customer's name is mandatory.")
        private String name;

        @NotBlank(message = "The customer's email is mandatory.")
        @Email(message = "Invalid email")
        private String email;

        @NotNull(message = "Select the payment method to be used as your default.")
        private TiposPamentos tiposPamentoPadrao;



}
