package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.ClienteDto;
import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody ClienteDto dto) {
        var cliente = clienteService.saveCliente(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll() {
        var cliente = clienteService.findAllCliente();
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        var cliente = clienteService.findClienteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody ClienteDto dto) {
        var cliente = clienteService.updateCliente(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }
}
