package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import Api_Rest.Pedidos.clientes.entity.TiposPamentos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   Optional<Cliente> findByEmail(String email);

   List<Cliente> findByNameContainingIgnoreCase(String name);

   List<Cliente> findByTiposPamentos(TiposPamentos tiposPamentos);
}
