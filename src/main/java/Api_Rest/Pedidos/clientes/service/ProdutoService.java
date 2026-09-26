package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import Api_Rest.Pedidos.clientes.dto.ProdutoResumoDto;
import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.entity.Produto;
import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
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
    public Produto saveProduto(ProdutoDto dto){
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                        .orElseThrow(() -> new ResourceExceptionHandler("Este cliente nao esta cadastrado!"));


       Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setPreco(dto.getPreco());
        produto.setDescricao(dto.getDescricao());
        produto.setStatusPedido(dto.getStatusPedido());
        produto.setCliente(cliente);

        return produtoRepository.save(produto);

    }

    public List<Produto> findAllProduto(){
        return produtoRepository.findAll();
    }

    public List<Produto> findByStatus(StatusPedido statusPedido){

        if (statusPedido == null){
            return produtoRepository.findAll();
        }

       return produtoRepository.findByStatusPedido(statusPedido);
    }

    public Produto findByIdProduto(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Product Id: "+ id +" Not Found"));
    }


    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }


    @Transactional
    public Produto updateProduto(Long id, ProdutoResumoDto dto){
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
