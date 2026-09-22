package Api_Rest.Pedidos.clientes.exception;

import java.util.Map;

public record ErrorValidation(Map<String , String> errors) {
}
