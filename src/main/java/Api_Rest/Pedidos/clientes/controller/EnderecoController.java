package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.endereco_dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import Api_Rest.Pedidos.clientes.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/endereco")
@RequiredArgsConstructor
@Tag(name = "Enderecos", description = "Metodo Crud para enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @PostMapping
    @Operation(summary = "Cadastro Endereco", description = "Metodo para cadastrar endereco")
    public ResponseEntity adicionarEndereco(@Valid @RequestBody EnderecoRequestDto enderecoRequestDto) {
        enderecoService.criarEndereco(enderecoRequestDto);
        return  ResponseEntity.status(HttpStatus.CREATED).body("successfully created");
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar Endereco", description = "Metodo para atualizar endereco")
    public ResponseEntity<Endereco> updateEndereco(@Valid @PathVariable Long id, @Valid @RequestBody EnderecoRequestDto dto) {
        enderecoService.updateEndereco(id, dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar Endereco", description = "Metodo para deletar endereco")
    public ResponseEntity delete(@PathVariable Long id) {
        enderecoService.deleteEndereco(id);
        return  ResponseEntity.status(HttpStatus.OK).body("Delet successfully");
    }
}
