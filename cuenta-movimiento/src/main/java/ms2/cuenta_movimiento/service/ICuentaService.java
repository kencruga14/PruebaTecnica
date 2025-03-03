package ms2.cuenta_movimiento.service;

import ms2.cuenta_movimiento.dto.CuentaDto;

import java.util.List;

public interface ICuentaService {
    List<CuentaDto> getAll();
    CuentaDto save(CuentaDto cliente);
    CuentaDto update(Long id, CuentaDto clienteDto);
    void delete(Long id);
}
