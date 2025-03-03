package ms1.cliente_persona.mapper;

import ms1.cliente_persona.dto.PersonaDto;
import ms1.cliente_persona.model.Persona;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonaMapper {
    PersonaMapper MAPPER = Mappers.getMapper(PersonaMapper.class);

    Persona toEntity(PersonaDto personaDto);

    PersonaDto toDto(Persona persona);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Persona partialUpdate(PersonaDto personaDto, @MappingTarget Persona persona);
}