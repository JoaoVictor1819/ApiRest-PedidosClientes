package Api_Rest.Pedidos.clientes.repository;

import Api_Rest.Pedidos.clientes.dto.projection.ClienteProjection;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPagamentos;
import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   Optional<Cliente> findByEmail(String email);

   List<Cliente> findByNameContainingIgnoreCase(String name);

   List<Cliente> findByTiposPagamentos(TiposPagamentos tiposPagamentos);

   @Query(value = "SELECT a FROM Cliente a JOIN FETCH a.endereco")
   Optional<Endereco> findByIdFetch(Long clienteId);


   @NativeQuery(value =
           "SELECT c.id AS id," +
                   " c.nome_cliente AS name," +
                   " c.email_cliente AS email," +
                   " c.tipos_pagamentos AS tiposPagamentos," +
                   " e.id AS enderecoId," +
                   " e.numero_residencia AS numero," +
                   " e.cidade AS cidade," +
                   " e.estado AS estado," +
                   " e.cep AS cep " +
                   "FROM cliente c " +
                   "LEFT JOIN enderecos e ON c.endereco_id = e.id")
   List<ClienteProjection> getAllClientes();


    @NativeQuery(value =
            "SELECT c.id AS id," +
                    " c.nome_cliente AS name," +
                    " c.email_cliente AS email," +
                    " c.tipos_pagamentos AS tiposPagamentos," +
                    " e.id AS enderecoId," +
                    " e.numero_residencia AS numero," +
                    " e.cidade AS cidade," +
                    " e.estado AS estado," +
                    " e.cep AS cep " +
                    "FROM cliente c " +
                    "LEFT JOIN enderecos e ON c.endereco_id = e.id",
    countQuery = "SELECT COUNT(*) " +
            "FROM cliente c" +
            " LEFT JOIN enderecos e" +
            " ON c.endereco_id = e.id")
    Page<ClienteProjection> getAllClientesPage(Pageable pageable);
}
