package ms1.cliente_persona.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.io.Serializable;

@Value
public class PersonaDto {


    Long personaid;

    @NotNull
    private String nombre;

    @NotNull
    private String genero;

    @NotNull
    @Min(1)
    @Positive
    private Long edad;

    @NotNull
    private String identificacion;

    @NotNull
    private String direccion;

    @NotNull
    private String telefono;
}