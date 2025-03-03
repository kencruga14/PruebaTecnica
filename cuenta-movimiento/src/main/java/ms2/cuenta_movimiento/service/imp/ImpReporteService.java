package ms2.cuenta_movimiento.service.imp;

import lombok.AllArgsConstructor;
import ms2.cuenta_movimiento.dto.ReporteDto;
import ms2.cuenta_movimiento.exception.cuenta.CuentaNotFoundException;
import ms2.cuenta_movimiento.exception.generico.DatabaseOperationException;
import ms2.cuenta_movimiento.exception.movimiento.MovimientoNotFoundException;
import ms2.cuenta_movimiento.model.Cuenta;
import ms2.cuenta_movimiento.model.Movimiento;
import ms2.cuenta_movimiento.repository.CuentaRepository;
import ms2.cuenta_movimiento.repository.MovimientoRepository;
import ms2.cuenta_movimiento.service.IReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ImpReporteService implements IReporteService {
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<ReporteDto> getReportByDateAndCuenta(Date fechaInicio, Date fechaFin, String numeroCuenta) {

        return setReporteDto(fechaInicio, fechaFin, numeroCuenta);
    }

    public List<ReporteDto> setReporteDto(Date fechaInicio, Date fechaFin, String numeroCuenta) {

            if (!cuentaRepository.existsByNumerocuenta(numeroCuenta)) {
                throw new CuentaNotFoundException("No existe cuenta con el número de cuenta ingresado");
            }
            Cuenta cuenta = cuentaRepository.findByNumerocuenta(numeroCuenta);
            List<Movimiento> movimientos = movimientoRepository.findByCuentaAndFechaBetween(cuenta.getCuentaid(), fechaInicio, fechaFin);
            if (movimientos.isEmpty()) {
                throw new MovimientoNotFoundException("No existen movimientos para la cuenta ingresada en el rango de fechas ingresado");
            }
        try {

            String url1 = "http://cliente-persona:8080/api/v1/clientes/ById/" + cuenta.getClienteid();
            ResponseEntity<String> response = restTemplate.getForEntity(url1, String.class);
            String cliente = response.getBody().toString();
            return movimientos.stream().map(movimiento -> {
                BigDecimal saldoInicial = movimiento.getTipomovimiento().equals("DEPOSITO") ? movimiento.getSaldo().subtract(movimiento.getValor()) : movimiento.getSaldo().add(movimiento.getValor());
                ReporteDto reporteDto = new ReporteDto();
                reporteDto.setFecha(movimiento.getFecha());
                reporteDto.setCliente(cliente);
                reporteDto.setNumeroCuenta(movimiento.getCuenta().getNumerocuenta());
                reporteDto.setTipo(movimiento.getCuenta().getTipocuenta());
                reporteDto.setSaldoInicial(saldoInicial);
                reporteDto.setEstado(movimiento.getCuenta().getEstado());
                reporteDto.setMovimiento(movimiento.getValor());
                reporteDto.setSaldoDisponible(movimiento.getSaldo());
                return reporteDto;
            }).toList();
        }catch (Exception e){
            System.out.println("error al obtener reporte: "+e.getMessage());
            throw new DatabaseOperationException("Error base de dataos:",e);
        }
    }
}
