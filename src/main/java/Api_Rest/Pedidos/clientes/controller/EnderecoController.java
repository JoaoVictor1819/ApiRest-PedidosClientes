package Api_Rest.Pedidos.clientes.controller;


import Api_Rest.Pedidos.clientes.dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.Endereco;
import Api_Rest.Pedidos.clientes.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
