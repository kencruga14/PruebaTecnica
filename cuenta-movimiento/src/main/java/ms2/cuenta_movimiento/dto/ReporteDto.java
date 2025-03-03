package ms2.cuenta_movimiento.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
@Data
public class ReporteDto {
    private Date fecha;
    private String cliente;
    private String numeroCuenta;
    private String tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    private BigDecimal movimiento;
    private BigDecimal saldoDisponible;
}
