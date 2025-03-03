package ms1.cliente_persona.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cliente", schema = "banco")
@Data
public class Cliente extends Persona{

    @Column(nullable = false, length = 50)
    private String contrasena;

    @Column(nullable = false)
    private Boolean  estado;

}
