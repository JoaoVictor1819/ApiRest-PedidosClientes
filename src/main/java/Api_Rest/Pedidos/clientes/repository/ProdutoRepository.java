package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.dto.projection.ProdutoProjection;
import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import Api_Rest.Pedidos.clientes.entity.produto.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @NativeQuery(value =
            "SELECT p.id AS id," +
                    " p.Nome_Produto AS nome," +
                    " p.Descricao_Produto AS descricao," +
                    " p.Preco_Produto AS preco," +
                    " p.status_pedido AS statusPedido," +
                    " c.id AS clienteId," +
                    " c.nome_cliente AS clienteNome " +
                    "FROM pedidos p " +
                    "LEFT JOIN cliente c ON p.cliente_id = c.id",
            countQuery = "SELECT COUNT(*) " +
                    "FROM pedidos p" +
                    " LEFT JOIN cliente c" +
                    " ON p.cliente_id = c.id")
    Page<ProdutoProjection> getAllProdutos(PageRequest pageable);





}
