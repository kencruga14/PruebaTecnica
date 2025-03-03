package ms2.cuenta_movimiento.mapper;

import ms2.cuenta_movimiento.dto.CuentaDto;
import ms2.cuenta_movimiento.model.Cuenta;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CuentaMapper {
    Cuenta toEntity(CuentaDto cuentaDto);

    @AfterMapping
    default void linkMovimientos(@MappingTarget Cuenta cuenta) {
        cuenta.getMovimientos().forEach(movimiento -> movimiento.setCuenta(cuenta));
    }

    CuentaDto toDto(Cuenta cuenta);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Cuenta partialUpdate(CuentaDto cuentaDto, @MappingTarget Cuenta cuenta);
}