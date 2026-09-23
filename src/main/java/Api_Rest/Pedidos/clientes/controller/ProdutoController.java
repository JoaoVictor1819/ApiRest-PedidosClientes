package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.ProdutoDto;
import Api_Rest.Pedidos.clientes.entity.Produto;
import Api_Rest.Pedidos.clientes.service.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@Tag(name = "Produtos", description = "Metodo Crud para produto")
public class ProdutoController {

    ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity cadastrarProduto(@RequestBody @Valid ProdutoDto dto){
        produtoService.saveProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Produto cadastrado com suscesso!");
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(){
        var produto = produtoService.findAllProduto();
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id){
        var produto = produtoService.findByIdProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Produto> atualizarProduto(@PathVariable @Valid Long id, @RequestBody @Valid ProdutoDto dto){
        var produto = produtoService.updateProduto(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }
}
