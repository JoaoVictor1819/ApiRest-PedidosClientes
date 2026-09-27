package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.ClienteDto;
import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import Api_Rest.Pedidos.clientes.dto.ProdutoResumoDto;
import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.entity.TiposPamentos;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
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

    public void saveCliente(ClienteDto dto){
        Cliente cliente = clienteRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (cliente != null){
            throw new BadRequestExceptionHandler("Este email de cliente ja existe!");
        }


        clienteRepository.save(Cliente.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .tiposPamentos(dto.getTiposPamentoPadrao())
                .build());

    }

    public List<ClienteDto> findAllCliente(){
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(this::toResponseDto)
                .toList();

    }

    private ClienteDto toResponseDto(Cliente cliente){
        List<ProdutoResumoDto> produtoResumoDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResumoDto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();
        
        return new ClienteDto(cliente.getId(), cliente.getName(),cliente.getEmail(), cliente.getTiposPamentos(), produtoResumoDtos);
    }

    public List<ClienteDto> findByName(String name){
        List<Cliente> clientesNome = clienteRepository.findByNameContainingIgnoreCase(name);

        return clientesNome.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ClienteDto findClienteById(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Cliente nao encontrado"));

        List<ProdutoResumoDto> produtoResumoDtos = cliente.getProdutos().stream()
                .map(p -> new ProdutoResumoDto(p.getId(), p.getNome(), p.getDescricao(), p.getPreco(), p.getStatusPedido()))
                .toList();

        return new ClienteDto(cliente.getId(),cliente.getName(), cliente.getEmail(), cliente.getTiposPamentos(), produtoResumoDtos);
    }

    public List<ClienteDto> findByPagamento(TiposPamentos tiposPamentos){
        List<Cliente> clientes = clienteRepository.findByTiposPamentos(tiposPamentos);

        return  clientes.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void deleteCliente(Long id){
        if (!clienteRepository.existsById(id)) {
            throw new ResourceExceptionHandler("Cliente id: "+ id + "Not Found!");
        }

        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Long id, ClienteDto dto){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Cliente id: "+ id + "Not Found!"));


        cliente.setName(dto.getName());
        cliente.setEmail(dto.getEmail());

        return clienteRepository.save(cliente);
    }
}
