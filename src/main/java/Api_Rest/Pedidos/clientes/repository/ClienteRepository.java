package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
