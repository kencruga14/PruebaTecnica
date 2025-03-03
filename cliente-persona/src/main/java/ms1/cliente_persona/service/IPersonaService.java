package ms1.cliente_persona.service;

import ms1.cliente_persona.dto.PersonaDto;

import java.util.List;

public interface IPersonaService {
    List<PersonaDto> getAll();
    PersonaDto save(PersonaDto persona);
    PersonaDto update(Long id, PersonaDto personaDto);
    void delete(Long id);
}
