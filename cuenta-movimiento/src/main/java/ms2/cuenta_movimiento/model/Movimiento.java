package ms2.cuenta_movimiento.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento", schema = "banco")
@Data
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movimientoid;

    private Date fecha;

    @NotNull
    private String tipomovimiento;

    @NotNull
    private BigDecimal valor;

    @NotNull
    private BigDecimal saldo;

    @ManyToOne
    @JoinColumn(name = "cuentaid")
    private Cuenta cuenta;

    @PrePersist
    public void prePersist() {
        this.fecha = Date.valueOf(LocalDateTime.now().toLocalDate());
    }

}
