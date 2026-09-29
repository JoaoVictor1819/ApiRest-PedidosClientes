package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteRequestDto;
import Api_Rest.Pedidos.clientes.dto.cliente_dto.ClienteResponseDto;
import Api_Rest.Pedidos.clientes.entity.cliente.TiposPamentos;
import Api_Rest.Pedidos.clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
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
    public ResponseEntity save(@Valid @RequestBody ClienteRequestDto dto) {
        clienteService.saveCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Client successfully created");
    }

    @GetMapping
    @Operation(summary = "Metodo Pesquisa", description = "Metodo feito para ver todos os clientes!")
    public ResponseEntity<List<ClienteResponseDto>> findAll() {
        var cliente = clienteService.findAllCliente();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/search/tipos_pagamentos")
    @Operation(summary = "Filtar por tipo de pagamento", description = "Metodo que ver os pedidos feitos com cada tipo de pagamento")
    public ResponseEntity<List<ClienteResponseDto>> findByPagamento(TiposPamentos tiposPamentos){
        var cliente = clienteService.findByPagamento(tiposPamentos);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/search/{id}")
    @Operation(summary = "Metodo Pesquisa Filtrada", description = "Metodo feito para ver clientes por id!")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable Long id) {
        var cliente = clienteService.findClienteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/search/name")
    @Operation(summary = "Pesquisa por nome", description = "Pesquisa um usuario por nome do proprio")
    private ResponseEntity<List<ClienteResponseDto>> findByName(@RequestParam (required = false )String name) {
        List<ClienteResponseDto> cliente = clienteService.findByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }


    @PutMapping("/updates/{id}")
    @Operation(summary = "Metodo Modificar", description = "Metodo feito para modificar cliente por id!")
    public ResponseEntity updateCliente(@PathVariable @Valid Long id, @RequestBody @Valid ClienteRequestDto dto) {
        clienteService.updateCliente(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body("Data successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Metodo Deletar", description = "Metodo feito para deletar cliente por id!")
    public ResponseEntity deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.status(HttpStatus.OK).body("successfully deleted");
    }
}
