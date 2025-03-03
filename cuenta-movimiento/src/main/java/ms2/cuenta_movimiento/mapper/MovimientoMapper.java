package ms2.cuenta_movimiento.mapper;

import ms2.cuenta_movimiento.dto.MovimientoDto;
import ms2.cuenta_movimiento.model.Movimiento;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MovimientoMapper {
    Movimiento toEntity(MovimientoDto movimientoDto);

    MovimientoDto toDto(Movimiento movimiento);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Movimiento partialUpdate(MovimientoDto movimientoDto, @MappingTarget Movimiento movimiento);
}