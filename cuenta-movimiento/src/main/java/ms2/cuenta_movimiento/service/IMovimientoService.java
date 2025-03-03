package ms2.cuenta_movimiento.service;

import ms2.cuenta_movimiento.dto.MovimientoDto;

import java.util.List;

public interface IMovimientoService {
    List<MovimientoDto> getAll();
    MovimientoDto save(MovimientoDto movimientoDto);
    MovimientoDto update(Long id, MovimientoDto movimientoDto);
    void delete(Long id);
}
