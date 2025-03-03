package ms2.cuenta_movimiento.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ms2.cuenta_movimiento.dto.MovimientoDto;
import ms2.cuenta_movimiento.exception.cuenta.CuentaNotFoundException;
import ms2.cuenta_movimiento.exception.generico.DatabaseOperationException;
import ms2.cuenta_movimiento.exception.generico.SaldoInsuficienteException;
import ms2.cuenta_movimiento.exception.movimiento.MovimientoNotFoundException;
import ms2.cuenta_movimiento.exception.movimiento.TipoMovimientoInvalidoException;
import ms2.cuenta_movimiento.mapper.MovimientoMapper;
import ms2.cuenta_movimiento.model.Cuenta;
import ms2.cuenta_movimiento.model.Movimiento;
import ms2.cuenta_movimiento.repository.CuentaRepository;
import ms2.cuenta_movimiento.repository.MovimientoRepository;
import ms2.cuenta_movimiento.service.IMovimientoService;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ImpMovimientoService implements IMovimientoService {
    private final MovimientoRepository movimientoRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoMapper movimientoMapper;

    @Override
    public List<MovimientoDto> getAll() {
        try {
            return movimientoRepository.findAll().stream().map(movimientoMapper::toDto).toList();
        } catch (Exception e) {
            throw new DatabaseOperationException("Error al obtener movimientos: ", e);
        }
    }

    @Override
    @Transactional
    public MovimientoDto save(MovimientoDto movimientoDto) {

        if (!cuentaRepository.existsByNumerocuenta(movimientoDto.getNumerocuenta())) {
            throw new CuentaNotFoundException("No existe una cuenta con el número de cuenta ingresado");
        }

        Cuenta cuenta = cuentaRepository.findByNumerocuenta(movimientoDto.getNumerocuenta());
        BigDecimal saldoActual = cuenta.getSaldoinicial();
        BigDecimal valorMovimiento = movimientoDto.getValor();

        if (movimientoDto.getTipomovimiento().toUpperCase().equals("RETIRO") || valorMovimiento.compareTo(BigDecimal.ZERO) < 0) {
            if (saldoActual.compareTo(valorMovimiento) < 0) {
                throw new SaldoInsuficienteException("El saldo de la cuenta es insuficiente para realizar el retiro");
            }
            saldoActual = saldoActual.subtract(valorMovimiento.abs());
        } else if (movimientoDto.getTipomovimiento().toUpperCase().equals("DEPOSITO")) {
            saldoActual = saldoActual.add(valorMovimiento);
        } else {
            throw new TipoMovimientoInvalidoException("Tipo de movimiento no válido");
        }
        try {
        Movimiento movimiento = movimientoMapper.toEntity(movimientoDto);
        movimiento.setCuenta(cuenta);
        movimiento.setSaldo(saldoActual);
        movimientoRepository.save(movimiento);

        cuenta.setSaldoinicial(movimiento.getSaldo());
        cuentaRepository.save(cuenta);

        return movimientoMapper.toDto(movimiento);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error al crear movimiento: ", e);
        }
    }

    @Override
    public MovimientoDto update(Long id, MovimientoDto movimientoDto) {

            Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new MovimientoNotFoundException("No existe un movimiento con el id ingresado"));
            Cuenta cuenta = cuentaRepository.findByNumerocuenta(movimientoDto.getNumerocuenta());

            if (!cuentaRepository.existsByNumerocuenta(movimientoDto.getNumerocuenta())) {
                throw new CuentaNotFoundException("No existe una cuenta con el número de cuenta ingresado");
            }
            if (movimientoDto.getTipomovimiento().toUpperCase().equals("RETIRO")) {
                if (cuenta.getSaldoinicial().compareTo(movimientoDto.getValor()) < 0) {
                    throw new SaldoInsuficienteException("El saldo de la cuenta es insuficiente para realizar el retiro");
                }
            }
        try {
            movimiento = movimientoMapper.toEntity(movimientoDto);
            movimiento.setMovimientoid(id);
            movimiento.setCuenta(cuenta);
            movimientoDto = movimientoMapper.toDto(movimientoRepository.save(movimiento));
            movimientoDto.setNumerocuenta(cuenta.getNumerocuenta());
            return movimientoDto;
        } catch (Exception e) {
            throw new DatabaseOperationException("Error al actualizar movimiento: ", e);
        }
    }

    @Override
    public void delete(Long id) {

        if(!movimientoRepository.existsById(id)) {
            throw new MovimientoNotFoundException("No existe movimiento con el id ingresado");
        }
        try {
        movimientoRepository.deleteById(id);
        } catch (Exception e) {
            throw new DatabaseOperationException("Error al eliminar movimiento: ", e);
        }
    }
}
