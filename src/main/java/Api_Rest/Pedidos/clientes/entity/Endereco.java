package Api_Rest.Pedidos.clientes.entity;


import Api_Rest.Pedidos.clientes.dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Enderecos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cep;

    private String estado;

    private String cidade;

    private Integer numeroResidencia;


private Endereco (EnderecoRequestDto dto){
    this.cep = dto.getCep();
    this.estado = dto.getEstado();
    this.cidade = dto.getCidade();
    this.numeroResidencia = dto.getNumeroResidencia();
}

}
