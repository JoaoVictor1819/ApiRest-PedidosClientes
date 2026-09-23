package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.ClienteDto;
import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
@Tag(name = "Cliente", description = "Metodos Crud para Cliente")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @Operation(summary = "Metodo Salvar", description = "Metodo feito para cadastrar clientes!")
    public ResponseEntity save(@RequestBody @Valid ClienteDto dto) {
        clienteService.saveCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Criado com suscesso!");
    }

    @GetMapping
    @Operation(summary = "Metodo Pesquisa", description = "Metodo feito para ver todos os clientes!")
    public ResponseEntity<List<Cliente>> findAll() {
        var cliente = clienteService.findAllCliente();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Metodo Pesquisa Filtrada", description = "Metodo feito para ver clientes por id!")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        var cliente = clienteService.findClienteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Metodo Modificar", description = "Metodo feito para modificar cliente por id!")
    public ResponseEntity<Cliente> updateCliente(@PathVariable @Valid Long id, @RequestBody @Valid ClienteDto dto) {
        var cliente = clienteService.updateCliente(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Metodo Deletar", description = "Metodo feito para deletar cliente por id!")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso!");
    }
}
