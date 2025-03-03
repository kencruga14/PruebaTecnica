package ms1.cliente_persona.mapper;

import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.dto.ClientePersonaDto;
import ms1.cliente_persona.dto.PersonaDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteDto_PersonaDtoMapper {
    ClienteDto_PersonaDtoMapper MAPPER = Mappers.getMapper(ClienteDto_PersonaDtoMapper.class);

    ClienteDto toEntity(ClientePersonaDto clientePersonaDto);

    PersonaDto toDto(ClienteDto clienteDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ClienteDto partialUpdate(ClientePersonaDto clientePersonaDto, @MappingTarget ClienteDto clienteDto);
}