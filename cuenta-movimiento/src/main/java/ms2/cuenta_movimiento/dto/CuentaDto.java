package ms2.cuenta_movimiento.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;


@Data
public class CuentaDto implements Serializable {
    Long cuentaid;
    String numerocuenta;
    @NotNull
    String tipocuenta;
    @NotNull
    BigDecimal saldoinicial;
    @NotNull
    Boolean estado;
    @NotNull
    Long clienteid;
}