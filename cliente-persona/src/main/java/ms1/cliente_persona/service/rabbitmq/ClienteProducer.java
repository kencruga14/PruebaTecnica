package ms1.cliente_persona.service.rabbitmq;

import lombok.AllArgsConstructor;
import ms1.cliente_persona.dto.ClienteDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteProducer {

    private final RabbitTemplate rabbitTemplate;

    public void enviarCliente(ClienteDto clienteDto) {
        rabbitTemplate.convertAndSend("cliente.creado", clienteDto);
    }

}
