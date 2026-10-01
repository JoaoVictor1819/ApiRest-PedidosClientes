package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.endereco_dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import Api_Rest.Pedidos.clientes.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity adicionarEndereco(@Valid @RequestBody EnderecoRequestDto enderecoRequestDto) {
        enderecoService.criarEndereco(enderecoRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body("successfully created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateEndereco(@Valid @PathVariable Long id, @Valid @RequestBody EnderecoRequestDto dto) {
        enderecoService.updateEndereco(id, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Delet successfully");
    }
}
