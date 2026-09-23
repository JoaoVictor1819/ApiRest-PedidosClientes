package Api_Rest.Pedidos.clientes.exception;

public class BadRequestExceptionHandler extends RuntimeException {
    public BadRequestExceptionHandler() {
        super("Erro na chamada");
    }

    public BadRequestExceptionHandler(String message) {
        super(message);
    }
}
