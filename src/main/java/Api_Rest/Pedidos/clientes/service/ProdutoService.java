package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoRequestDto;
import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoUpdateDto;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import Api_Rest.Pedidos.clientes.entity.produto.StatusPedido;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;
    ClienteRepository clienteRepository;


    public ProdutoService(ProdutoRepository produtoRepository, ClienteRepository clienteRepository) {
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
    }



    @Transactional
    public Produto saveProduto(ProdutoRequestDto dto){
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                        .orElseThrow(() -> new ResourceExceptionHandler("Client Not Found"));


       Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setStatusPedido(dto.getStatusPedido());
        produto.setCliente(cliente);

        return produtoRepository.save(produto);

    }

    public List<ProdutoRequestDto> findAllProduto(){
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(this::toResponseDto)
                .toList();

    }

    private ProdutoRequestDto toResponseDto(Produto produto){
        return new ProdutoRequestDto(produto.getDescricao(), produto.getNome(), produto.getPreco(), produto.getId(), produto.getStatusPedido() );
    }

    public List<ProdutoRequestDto> findByStatus(StatusPedido statusPedido){
        List<Produto> produtoStatus = produtoRepository.findByStatusPedido(statusPedido);

        return produtoStatus.stream()
                .map(this::toResponseDto)
                .toList();
    }

    public ProdutoRequestDto findByIdProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("This product ID does not exist"));

        return new ProdutoRequestDto(produto.getDescricao(), produto.getNome(), produto.getPreco(), produto.getId(), produto.getStatusPedido() );
    }


    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }


    @Transactional
    public Produto updateProduto(Long id, ProdutoUpdateDto dto){
        produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Product Id: "+ id +" Not Found"));

        var produto = new Produto(dto);
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setStatusPedido(dto.getStatusPedido());

        return  produtoRepository.save(produto);
    }
}
