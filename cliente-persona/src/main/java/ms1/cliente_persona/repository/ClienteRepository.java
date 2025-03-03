package ms1.cliente_persona.repository;

import ms1.cliente_persona.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c.nombre FROM Cliente c WHERE c.personaid = ?1")
    String nombreCliente(Long personaid);
}