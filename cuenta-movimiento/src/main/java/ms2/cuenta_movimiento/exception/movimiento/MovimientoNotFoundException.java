package ms2.cuenta_movimiento.exception.movimiento;

public class MovimientoNotFoundException extends RuntimeException {
    public MovimientoNotFoundException(String message) {
        super(message);
    }
}