package ms2.cuenta_movimiento.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;


@Data
public class MovimientoDto implements Serializable {
    Long movimientoid;
    Date fecha;
    @NotNull
    String tipomovimiento;
    @NotNull
    BigDecimal valor;
    BigDecimal saldo;
    @NotNull
    String numerocuenta;
}