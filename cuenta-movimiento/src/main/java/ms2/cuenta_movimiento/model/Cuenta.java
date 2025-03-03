package ms2.cuenta_movimiento.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cuenta", schema = "banco")
@Data
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cuentaid;

    @Column(unique = true)
    private String numerocuenta;

    @NotNull
    private String tipocuenta;

    @NotNull
    private BigDecimal saldoinicial;

    @NotNull
    private Boolean estado;

    @NotNull
    private Long clienteid;

    @JsonIgnore
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Movimiento> movimientos;

    public Cuenta() {
        this.movimientos = new ArrayList<>(); // Inicialización aquí
    }
}
