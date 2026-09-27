package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import Api_Rest.Pedidos.clientes.entity.produto.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    Optional<Produto> findByNome(String nome);

    List<Produto> findByStatusPedido(StatusPedido statusPedido);


    @Query(value = """
    SELECT p
    FROM Produto p WHERE p.nome = :nome
    """)
    Optional<Produto> findByNomeJpql(String nome);


    @NativeQuery(value = """
    SELECT p
    FROM pedidos p WHERE p.nome = :nome
    """)
    Optional<Produto> findByNomeNativeQuery(String nome);

}
