package ms2.cuenta_movimiento.repository;

import ms2.cuenta_movimiento.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    boolean existsByNumerocuenta(String numerocuenta);

    boolean existsByClienteid(Long clienteid);

    Cuenta findByNumerocuenta(String numerocuenta);


}