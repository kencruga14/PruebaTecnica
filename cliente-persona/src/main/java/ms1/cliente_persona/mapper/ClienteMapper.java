package ms1.cliente_persona.mapper;

import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.dto.PersonaDto;
import ms1.cliente_persona.model.Cliente;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClienteMapper {
    ClienteMapper MAPPER = Mappers.getMapper(ClienteMapper.class);

    Cliente toEntity(ClienteDto clienteDto);

    ClienteDto toDto(Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cliente partialUpdate(ClienteDto clienteDto, @MappingTarget Cliente cliente);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PersonaDto partialUpdatePersona(ClienteDto clienteDto, @MappingTarget PersonaDto personaDto);


}