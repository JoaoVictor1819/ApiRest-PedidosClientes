package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteRequestDto;
import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteResponseDto;
import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoResumoDto;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .tiposPamentos(dto.getTiposPamentoPadrao())
                .build());

    }

    public List<ClienteResponseDto> findAllCliente(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(this::toResponseDto)
                .toList();

    }

    private ClienteResponseDto toResponseDto(Cliente cliente){
        List<ProdutoResumoDto> produtoResumoDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResumoDto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();
        
        return new ClienteResponseDto(cliente.getId(), cliente.getName(),cliente.getEmail(), cliente.getTiposPamentos(), produtoResumoDtos);
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

        List<ProdutoResumoDto> produtoResumoDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResumoDto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();

        return new ClienteResponseDto(cliente.getId(),cliente.getName(), cliente.getEmail(), cliente.getTiposPamentos(), produtoResumoDtos);
    }

    public List<ClienteResponseDto> findByPagamento(TiposPamentos tiposPamentos){
        List<Cliente> clientes = clienteRepository.findByTiposPamentos(tiposPamentos);

        return  clientes.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void deleteCliente(Long id){
        if (!clienteRepository.existsById(id)) {
            throw new ResourceExceptionHandler("Client id: "+ id + "Not Found!");
        }

        clienteRepository.deleteById(id);
    }


    @Transactional
    public void updateCliente(Long id, ClienteRequestDto dto){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Client id: "+ id + "Not Found!"));


       clienteRepository.save(Cliente.builder()
                       .name(dto.getName())
                       .email(dto.getEmail())
                       .tiposPamentos(dto.getTiposPamentoPadrao())
               .build());



    }
}
