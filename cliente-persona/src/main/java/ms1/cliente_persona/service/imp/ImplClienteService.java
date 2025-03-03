package ms1.cliente_persona.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.exception.ClienteAlreadyExistsException;
import ms1.cliente_persona.exception.ClienteNotFoundException;
import ms1.cliente_persona.exception.DatabaseOperationException;
import ms1.cliente_persona.mapper.ClienteMapper;
import ms1.cliente_persona.model.Cliente;
import ms1.cliente_persona.repository.ClienteRepository;
import ms1.cliente_persona.repository.PersonaRepository;
import ms1.cliente_persona.service.IClienteService;
import ms1.cliente_persona.service.rabbitmq.ClienteProducer;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ImplClienteService implements IClienteService {
    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;
    private final PersonaRepository personaRepository;
    private final ClienteProducer clienteProducer;

    @Override
    public List<ClienteDto> getAll() {
        try{
        return clienteRepository.findAll().stream().map(clienteMapper::toDto).toList();
        }catch (Exception e){
            throw new DatabaseOperationException("error al obtener clientes: ",e);
        }
    }

    @Override
    @Transactional
    public ClienteDto save(ClienteDto clienteDto) {

            if (personaRepository.existsByIdentificacion(clienteDto.getIdentificacion())) {
                throw new ClienteAlreadyExistsException("Ya existe un cliente registrado con la identificación ingresada");
            }
        try {
            ClienteDto clienteDtoGuardado = clienteMapper.toDto(clienteRepository.save(clienteMapper.toEntity(clienteDto)));
            clienteProducer.enviarCliente(clienteDtoGuardado);
            return clienteDtoGuardado;
        }catch (DataAccessException e){
            throw new DatabaseOperationException("error al crear cliente: ",e);
        }
    }

    @Override
    public ClienteDto update(Long id, ClienteDto clienteDto) {

            Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        try {
            cliente = clienteMapper.toEntity(clienteDto);
            cliente.setPersonaid(id);
            return clienteMapper.toDto(clienteRepository.save(cliente));
        } catch (DataAccessException e) {
            throw new DatabaseOperationException("error al actualizar cliente: ", e);
        }
    }

    @Override
    public void delete(Long id) {

        if(!clienteRepository.existsById(id)){
            throw new ClienteNotFoundException(id);
        }
        try{
        clienteRepository.deleteById(id);
    }catch (DataAccessException e){
            throw new DatabaseOperationException("error al eliminar cliente: ",e);
        }
    }

    @Override
    public String getNombreCliente(Long id) {
        return clienteRepository.nombreCliente(id);
    }
}
