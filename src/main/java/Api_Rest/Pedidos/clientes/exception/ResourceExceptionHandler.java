package Api_Rest.Pedidos.clientes.exception;

public class ResourceExceptionHandler extends RuntimeException {


    public ResourceExceptionHandler() {
        super("Resource Not Found");
    }

    public ResourceExceptionHandler(String message) {
        super(message);
    }
}
