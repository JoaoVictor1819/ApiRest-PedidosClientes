package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.Produto;
import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
