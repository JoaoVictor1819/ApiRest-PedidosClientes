package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
