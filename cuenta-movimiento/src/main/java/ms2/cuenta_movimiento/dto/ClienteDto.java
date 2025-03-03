package ms2.cuenta_movimiento.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class ClienteDto implements Serializable {
    Long personaid;
    @NotNull
    String nombre;
    @NotNull
    String genero;
    @NotNull
    Long edad;
    @NotNull
    String identificacion;
    @NotNull
    String direccion;
    @NotNull
    String telefono;
    @NotNull
    String contrasena;
    @NotNull
    Boolean estado;

}
