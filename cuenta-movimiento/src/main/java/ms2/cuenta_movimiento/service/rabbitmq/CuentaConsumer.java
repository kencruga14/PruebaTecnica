package ms2.cuenta_movimiento.service.rabbitmq;

import lombok.AllArgsConstructor;
import ms2.cuenta_movimiento.dto.ClienteDto;
import ms2.cuenta_movimiento.dto.CuentaDto;
import ms2.cuenta_movimiento.exception.generico.DatabaseOperationException;
import ms2.cuenta_movimiento.service.ICuentaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@AllArgsConstructor
public class CuentaConsumer {
    private final ICuentaService cuentaService;

    @RabbitListener(queues = "cliente.creado")
    public void recibirMensaje(ClienteDto clienteDto) {
try {
    CuentaDto cuentaDto = new CuentaDto();
    cuentaDto.setClienteid(clienteDto.getPersonaid());
    cuentaDto.setSaldoinicial(new BigDecimal("0"));
    cuentaDto.setEstado(true);
    cuentaDto.setTipocuenta("Ahorros");
    cuentaDto.setNumerocuenta(generaNumeroCuenta());
    cuentaService.save(cuentaDto);
}catch (Exception e){
    throw new DatabaseOperationException("error al crear cuenta: ",e);
}
    }


    public String generaNumeroCuenta() {
        Random random = new Random();
        return String.valueOf(random.nextInt(900000) + 100000);
    }
}
