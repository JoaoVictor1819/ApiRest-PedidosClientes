package Api_Rest.Pedidos.clientes.dto;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDto(


        @NotBlank
        String name,

        @NotBlank
        @Email
        String email) {}
