package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import Api_Rest.Pedidos.clientes.entity.Produto;
import Api_Rest.Pedidos.clientes.entity.StatusPedido;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto saveProduto(ProdutoDto dto){
        var produto = new Produto(dto);
        return produtoRepository.save(produto);
    }

    public List<Produto> findAllProduto(){
        return produtoRepository.findAll();
    }

    public Produto findByIdProduto(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Product Id: "+ id +" Not Found"));
    }


    public void deleteProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public Produto updateProduto(Long id, ProdutoDto dto){
        produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Product Id: "+ id +" Not Found"));

        var produto = new Produto(dto);
        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setDescricao(dto.descricao());
        produto.setStatusPedido(dto.statusPedido());

        return  produtoRepository.save(produto);
    }
}
