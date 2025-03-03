package ms1.cliente_persona.service.imp;

import lombok.AllArgsConstructor;
import ms1.cliente_persona.dto.PersonaDto;
import ms1.cliente_persona.mapper.PersonaMapper;
import ms1.cliente_persona.model.Persona;
import ms1.cliente_persona.repository.PersonaRepository;
import ms1.cliente_persona.service.IPersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImplPersonaService implements IPersonaService {
    private final PersonaRepository personaRepository;
    private final PersonaMapper personaMapper;

    @Override
    public List<PersonaDto> getAll() {
        return personaRepository.findAll().stream().map(personaMapper::toDto).toList();
    }

    @Override
    public PersonaDto save(PersonaDto personaDto) {
        if(personaRepository.existsByIdentificacion(personaDto.getIdentificacion())){
            throw new RuntimeException("Ya existe un cliente registrado con la identificación ingresada");
        }
        try{
            return personaMapper.toDto(personaRepository.save(personaMapper.toEntity(personaDto)));
        }catch (Exception e){
            throw new RuntimeException("error al crear usuario: "+e.getMessage());
        }
    }

    @Override
    public PersonaDto update(Long id, PersonaDto personaDto) {
        Optional<Persona> persona = personaRepository.findById(id);
        if (persona.isEmpty()) {
            throw new RuntimeException("No existe un cliente registrado con el id ingresado");
        } else {
            return personaMapper.toDto(personaRepository.save(personaMapper.toEntity(personaDto)));
        }
    }

    @Override
    public void delete(Long id) {
        personaRepository.deleteById(id);
    }
}
