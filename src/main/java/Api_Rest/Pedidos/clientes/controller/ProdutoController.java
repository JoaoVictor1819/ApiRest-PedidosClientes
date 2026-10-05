package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.projection.ProdutoProjection;
import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoRequestDto;
import Api_Rest.Pedidos.clientes.dto.produto_dto.ProdutoUpdateDto;
import Api_Rest.Pedidos.clientes.entity.produto.Produto;
import Api_Rest.Pedidos.clientes.entity.produto.StatusPedido;
import Api_Rest.Pedidos.clientes.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@Tag(name = "Produtos", description = "Metodo Crud para produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    @Operation(summary = "Cadastro Produto", description = "Metodo para cadastrar produto")
    public ResponseEntity cadastrarProduto(@RequestBody @Valid ProdutoRequestDto dto){
        produtoService.saveProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product successfully registered");
    }

    @GetMapping
    @Operation(summary = "Ver produtos", description = "Metodo para visualizar todos os produtos cadastrados")
    public ResponseEntity<List<ProdutoRequestDto>> listarProdutos(){
        var produto = produtoService.findAllProduto();
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping("search/status_pedidos")
    @Operation(summary = "Filtrar Status", description = "Metodo que mostra produto por status filtrando para melhor visualizacao")
    public ResponseEntity<List<ProdutoRequestDto>> listarStatusPedidos(@RequestParam (required = false) StatusPedido statusPedido){
        List<ProdutoRequestDto> produto = produtoService.findByStatus(statusPedido);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @GetMapping("/page/{page}/size/{size}")
    public ResponseEntity<Page<ProdutoProjection>> listarProdutos(@PathVariable Integer page, @PathVariable Integer size){
        Page<ProdutoProjection> produto = produtoService.getAllProdutosPage(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }


    @GetMapping("search/{id}")
    @Operation(summary = "Filtrar por Id", description = "Metodo que pesquisa o produto por id")
    public ResponseEntity<ProdutoRequestDto> buscarProduto(@PathVariable Long id){
        var produto = produtoService.findByIdProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @PutMapping("/updates/{id}")
    @Operation(summary = "Atualizar produto", description = "Metodo que atualiza um produto ja cadastrado")
    public  ResponseEntity<Produto> atualizarProduto(@PathVariable @Valid Long id, @RequestBody @Valid ProdutoUpdateDto dto){
        var produto = produtoService.updateProduto(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletar produto", description = "Metodo que deleta um produto!")
    public ResponseEntity deletarProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return  ResponseEntity.status(HttpStatus.OK).body("successfully deleted" + id);
    }
}