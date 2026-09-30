package Api_Rest.Pedidos.clientes.service;

import Api_Rest.Pedidos.clientes.dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.Endereco;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoService {

    EnderecoRepository enderecoRepository;
    ClienteRepository clienteRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, ClienteRepository clienteRepository) {
        this.enderecoRepository = enderecoRepository;
        this.clienteRepository = clienteRepository;
    }

    public void criarEndereco(EnderecoRequestDto dto)throws ResourceExceptionHandler, BadRequestExceptionHandler {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceExceptionHandler("Este id nao foi encontrado e nao pode receber este endereco"));

        Endereco endereco = cliente.getEndereco();
        if (endereco != null){
            throw new BadRequestExceptionHandler("Este cliente ja possui um endereco cadastrado");
        }

        endereco = Endereco.builder()
                .cep(dto.getCep())
                .estado(dto.getEstado())
                .cidade(dto.getCidade())
                .numeroResidencia(dto.getNumeroResidencia())
                .build();

        Endereco enderecoCliente = enderecoRepository.save(endereco);

        cliente.setEndereco(enderecoCliente);
        clienteRepository.save(cliente);

    }
}
