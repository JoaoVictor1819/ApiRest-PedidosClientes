package Api_Rest.Pedidos.clientes.dto;

import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ProdutoDto{

         @NotBlank(message = "O nome do produto e obrigatorio")
         private String nome;

         @NotBlank
         @Size(min = 1, max = 100)
         private String descricao;

         @NotNull(message = "O preco nao poder receber um valo null")
         @DecimalMin(value = "0.01", inclusive = false, message = "O valor tem que ser maior que zero")
         private BigDecimal preco;

         private StatusPedido statusPedido;
}
