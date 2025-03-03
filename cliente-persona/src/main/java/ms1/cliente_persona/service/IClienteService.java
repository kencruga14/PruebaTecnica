package ms1.cliente_persona.service;

import ms1.cliente_persona.dto.ClienteDto;

import java.util.List;

public interface IClienteService {
    List<ClienteDto> getAll();
    ClienteDto save(ClienteDto cliente);
    ClienteDto update(Long id, ClienteDto clienteDto);
    void delete(Long id);
    String getNombreCliente(Long id);
}
