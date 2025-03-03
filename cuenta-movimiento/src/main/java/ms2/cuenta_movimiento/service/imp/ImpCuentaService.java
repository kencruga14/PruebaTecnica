package ms2.cuenta_movimiento.service.imp;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ms2.cuenta_movimiento.dto.CuentaDto;
import ms2.cuenta_movimiento.exception.generico.ClienteNotFoundException;
import ms2.cuenta_movimiento.exception.cuenta.CuentaAlreadyExistsException;
import ms2.cuenta_movimiento.exception.cuenta.CuentaNotFoundException;
import ms2.cuenta_movimiento.exception.generico.DatabaseOperationException;
import ms2.cuenta_movimiento.exception.generico.SaldoInsuficienteException;
import ms2.cuenta_movimiento.mapper.CuentaMapper;
import ms2.cuenta_movimiento.model.Cuenta;
import ms2.cuenta_movimiento.model.Movimiento;
import ms2.cuenta_movimiento.repository.CuentaRepository;
import ms2.cuenta_movimiento.repository.MovimientoRepository;
import ms2.cuenta_movimiento.service.ICuentaService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ImpCuentaService implements ICuentaService {
    private final CuentaRepository cuentaRepository;
    private final CuentaMapper cuentaMapper;
    private final MovimientoRepository movimientoRepository;

    @Override
    public List<CuentaDto> getAll() {
        try {
            return cuentaRepository.findAll().stream().map(cuentaMapper::toDto).toList();
        }catch (Exception e){
            throw new DatabaseOperationException("error al obtener cuentas: ",e);
        }
    }

    @Override
    @Transactional
    public CuentaDto save(CuentaDto cuentaDto) {

            if (cuentaRepository.existsByNumerocuenta(cuentaDto.getNumerocuenta())) {
                throw new CuentaAlreadyExistsException("Ya existe una cuenta con el número de cuenta ingresado");
            }
            if(cuentaDto.getSaldoinicial().compareTo(BigDecimal.ZERO) < 0){
                throw new SaldoInsuficienteException("El saldo inicial no puede ser negativo");
            }
        try {
            Cuenta cuenta = cuentaRepository.save(cuentaMapper.toEntity(cuentaDto));
            Movimiento movimiento = new Movimiento();
            movimiento.setCuenta(cuenta);
            movimiento.setValor(cuentaDto.getSaldoinicial());
            movimiento.setTipomovimiento("DEPOSITO");
            movimiento.setSaldo(cuentaDto.getSaldoinicial());
            movimientoRepository.save(movimiento);
            return cuentaMapper.toDto(cuenta);
        }catch (Exception e){
            throw new DatabaseOperationException("error al crear cuenta: ",e);
        }
    }

    @Override
    public CuentaDto update(Long id, CuentaDto cuentaDto) {
            Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new CuentaNotFoundException("No existe cuenta con el id ingresado"));
            cuenta = cuentaMapper.toEntity(cuentaDto);
            cuenta.setCuentaid(id);
            if (!cuentaRepository.existsByClienteid(cuentaDto.getClienteid())) {
                throw new ClienteNotFoundException("No existe cliente con el id ingresado");
            }
        try {
            return cuentaMapper.toDto(cuentaRepository.save(cuenta));
        } catch (Exception e) {
            throw new DatabaseOperationException("error al actualizar cuenta: ", e);
        }
    }

    @Override
    public void delete(Long id) {

        if(!cuentaRepository.existsById(id)) {
            throw new CuentaNotFoundException("No existe cuenta con el id ingresado");
        }
        try{
        cuentaRepository.deleteById(id);
        }catch (Exception e){
            throw new DatabaseOperationException("error al eliminar cuenta: ",e);
        }
    }
}
