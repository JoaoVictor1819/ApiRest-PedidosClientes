package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteUpdateDto;
import Api_Rest.Pedidos.clientes.dto.projection.ClienteProjection;
import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteRequestDto;
import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteResponseDto;
import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoResponseDto;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPagamentos;
import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;

@Service
public class ClienteService {

    ClienteRepository clienteRepository;
    ProdutoRepository produtoRepository;

    public ClienteService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    public void saveCliente(ClienteRequestDto dto){
        Cliente cliente = clienteRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (cliente != null){
            throw new BadRequestExceptionHandler("Email already exists, please try another one.");
        }

        clienteRepository.save(Cliente.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .tiposPagamentos(dto.getTiposPagamentoPadrao())
                .build());
    }

    public List<ClienteResponseDto> findAllCliente(){
        List<Cliente> clientes = clienteRepository.findAll();
        // Converte List<Cliente> -> List<ClienteResponseDto>
        return clientes.stream()
                .map(this::toResponseDto)// -> Esta chamando o metodo que mapeia a entidade para dto
                .toList();

    }

    private ClienteResponseDto toResponseDto(Cliente cliente){ // Metodo que faz a convercao de entidade para o dto
        List<ProdutoResponseDto> produtoResponseDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResponseDto(p.getId(), p.getNome(),p.getDataCriacao(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();
        
        return new ClienteResponseDto(cliente.getId(), cliente.getName(),cliente.getEmail(), cliente.getTiposPagamentos(),produtoResponseDtos, cliente.getEndereco());
    }

    public List<ClienteResponseDto> findByName(String name){
        List<Cliente> clientesNome = clienteRepository.findByNameContainingIgnoreCase(name);
        
        return clientesNome.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ClienteResponseDto findClienteById(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Client Not Found"));

        List<ProdutoResponseDto> produtoResponseDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResponseDto(p.getId(), p.getNome(),p.getDataCriacao(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();

        return new ClienteResponseDto(cliente.getId(),cliente.getName(), cliente.getEmail(), cliente.getTiposPagamentos(), produtoResponseDtos, cliente.getEndereco());
    }

    public List<ClienteResponseDto> findByPagamento(TiposPagamentos tiposPagamentos){
        List<Cliente> clientes = clienteRepository.findByTiposPagamentos(tiposPagamentos);

        return  clientes.stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Transactional
    public void deleteCliente(Long id){
      Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Client Not Found"));

     List<Long> ProdutosClienteIds = cliente.getProdutos().stream()
             .map(Produto::getId)
             .toList();

     produtoRepository.deleteAllById(ProdutosClienteIds);

     clienteRepository.deleteById(id);

    }


    @Transactional
    public Cliente updateCliente(Long id, ClienteUpdateDto dto){
         clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Client id: "+ id + "Not Found!"));


      var cliente = Cliente.builder()
                .name(dto.getName())
                .tiposPagamentos(TiposPagamentos.valueOf(dto.getTiposPagamentoPadrao()))
                .build();

      return clienteRepository.save(cliente);
    }

    public List<ClienteProjection> getAllClientes(){
        return clienteRepository.getAllClientes();
    }

    public Page<ClienteProjection> getAllClientesPageable(Integer page, Integer size){
        return clienteRepository.getAllClientesPage(PageRequest.of(page, size));
    }

}
