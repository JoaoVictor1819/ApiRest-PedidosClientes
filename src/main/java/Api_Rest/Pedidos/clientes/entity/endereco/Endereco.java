package Api_Rest.Pedidos.clientes.entity.endereco;


import Api_Rest.Pedidos.clientes.dto.endereco_dto.EnderecoRequestDto;
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

    @Column(name = "Endereco_cep",length = 9)
    private String cep;

    @Column(name = "Endereco_estado")
    private String estado;

    @Column(name = "Endereco_cidade")
    private String cidade;

    @Column(name = "Endereco_numeroResidencia")
    private Integer numeroResidencia;


private Endereco (EnderecoRequestDto dto){
    this.cep = dto.getCep();
    this.estado = dto.getEstado();
    this.cidade = dto.getCidade();
    this.numeroResidencia = dto.getNumeroResidencia();
}

}
