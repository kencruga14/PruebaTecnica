package ms1.cliente_persona.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ClienteDto}
 */
@Value
public class ClientePersonaDto implements Serializable {
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
}