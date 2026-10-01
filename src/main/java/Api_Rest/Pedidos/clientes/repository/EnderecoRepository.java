package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
