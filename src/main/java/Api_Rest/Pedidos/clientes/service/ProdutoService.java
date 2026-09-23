package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import Api_Rest.Pedidos.clientes.entity.Produto;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }


    @Transactional
    public void saveProduto(ProdutoDto dto){
       Produto produto = produtoRepository.findByNome(dto.getNome())
               .orElse(null);

       if (produto != null){
           throw new BadRequestExceptionHandler("Produto ja foi cadastrado!");
       }

       produtoRepository.save(Produto.builder()
               .nome(dto.getNome())
               .preco(dto.getPreco())
               .descricao(dto.getDescricao())
               .statusPedido(dto.getStatusPedido())
               .build());

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


    @Transactional
    public Produto updateProduto(Long id, ProdutoDto dto){
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
