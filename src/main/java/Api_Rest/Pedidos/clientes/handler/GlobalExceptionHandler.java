package Api_Rest.Pedidos.clientes.handler;


import Api_Rest.Pedidos.clientes.exception.ResourceExceptionHandler;
import Api_Rest.Pedidos.clientes.exception.RestErrorMenssage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.crypto.Data;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceExceptionHandler.class)
    private ResponseEntity<RestErrorMenssage> resouceNotFound(ResourceExceptionHandler ex){
        RestErrorMenssage error = RestErrorMenssage.builder()
                .data(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErrorMenssage> runtimeException(RuntimeException ex){
        RestErrorMenssage errorMenssage = RestErrorMenssage.builder()
                .data(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMenssage);
    }
}
