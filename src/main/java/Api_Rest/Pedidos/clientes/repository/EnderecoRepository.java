package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {


    List<Endereco> findByCidadeContainingIgnoreCase(String cidade);
}
