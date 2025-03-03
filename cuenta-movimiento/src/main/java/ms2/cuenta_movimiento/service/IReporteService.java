package ms2.cuenta_movimiento.service;

import ms2.cuenta_movimiento.dto.ReporteDto;

import java.sql.Date;
import java.util.List;

public interface IReporteService {
    List<ReporteDto> getReportByDateAndCuenta(Date fechaInicio, Date fechaFin, String numeroCuenta);

}
