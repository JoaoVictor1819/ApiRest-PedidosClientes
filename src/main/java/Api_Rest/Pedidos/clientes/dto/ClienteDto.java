package Api_Rest.Pedidos.clientes.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ClienteDto{

        @NotBlank
        private String name;

        @NotBlank
        private String email;

}
