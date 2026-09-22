package Api_Rest.Pedidos.clientes.service;


import Api_Rest.Pedidos.clientes.dto.ClienteDto;
import Api_Rest.Pedidos.clientes.entity.Cliente;
import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente saveCliente(ClienteDto dto){
        var cliente = new Cliente(dto);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> findAllCliente(){
        return  clienteRepository.findAll();
    }

    public Cliente findClienteById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Cliente id: "+ id + "Not Found!"));
    }

    public void deleteCliente(Long id){
        if (!clienteRepository.existsById(id)) {
            throw new ResourceExceptionHandler("Cliente id: "+ id + "Not Found!");
        }

        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Long id, ClienteDto dto){
        clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceExceptionHandler("Cliente id: "+ id + "Not Found!"));

        var cliente = new Cliente(dto);
        cliente.setName(dto.name());
        cliente.setEmail(dto.email());

        return clienteRepository.save(cliente);
    }
}
