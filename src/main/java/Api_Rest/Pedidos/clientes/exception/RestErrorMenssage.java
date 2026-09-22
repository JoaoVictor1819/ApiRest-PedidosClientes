package Api_Rest.Pedidos.clientes.exception;


import lombok.*;
import org.springframework.http.HttpStatus;

import javax.xml.crypto.Data;
import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestErrorMenssage {

    private Date data;
    private HttpStatus status;
    private String message;
}
