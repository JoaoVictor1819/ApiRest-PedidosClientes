package Api_Rest.Pedidos.clientes.service;

import Api_Rest.Pedidos.clientes.dto.endereco_dto.EnderecoRequestDto;
import Api_Rest.Pedidos.clientes.entity.endereco.Endereco;
import Api_Rest.Pedidos.clientes.entity.cliente.Cliente;
import Api_Rest.Pedidos.clientes.exception.BadRequestExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import Api_Rest.Pedidos.clientes.repository.EnderecoRepository;
import jakarta.validation.Valid;
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



        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }


    public void deleteEndereco(Long id)throws ResourceExceptionHandler{
        if (!enderecoRepository.existsById(id)){
            throw new ResourceExceptionHandler("Nao existe nenhum endereco cadastro com este id: "+id);
        }
        enderecoRepository.deleteById(id);
    }

    public void updateEndereco(Long id, @Valid EnderecoRequestDto dto){
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new ResourceExceptionHandler("este id nao existe"));

         enderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("nao existe nenhum endereco cadastrado com este id: "+id));


         var endereco = Endereco.builder()
                 .cep(dto.getCep())
                 .estado(dto.getEstado())
                 .cidade(dto.getCidade())
                 .numeroResidencia(dto.getNumeroResidencia())
                 .build();

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);


    }
}
