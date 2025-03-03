package ms2.cuenta_movimiento.repository;

import ms2.cuenta_movimiento.model.Cuenta;
import ms2.cuenta_movimiento.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    @Query("SELECT m FROM Movimiento m WHERE m.cuenta.cuentaid = ?1 AND m.fecha BETWEEN ?2 AND ?3")
    List<Movimiento> findByCuentaAndFechaBetween(Long cuenta, Date fechaInicio, Date fechaFin);
}