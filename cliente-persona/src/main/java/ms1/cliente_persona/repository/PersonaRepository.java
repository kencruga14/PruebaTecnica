package ms1.cliente_persona.repository;

import ms1.cliente_persona.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    boolean existsByIdentificacion(String identificacion);


}