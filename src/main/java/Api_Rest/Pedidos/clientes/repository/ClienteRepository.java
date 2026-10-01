package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   Optional<Cliente> findByEmail(String email);

   List<Cliente> findByNameContainingIgnoreCase(String name);

   List<Cliente> findByTiposPamentos(TiposPamentos tiposPamentos);

   @Query(value = "SELECT a FROM Cliente a JOIN FETCH a.endereco")
   Optional<Endereco> findByIdFetch(Long clienteId);
}
