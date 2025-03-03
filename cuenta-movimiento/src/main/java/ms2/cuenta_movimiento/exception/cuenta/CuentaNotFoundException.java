package ms2.cuenta_movimiento.exception.cuenta;

public class CuentaNotFoundException extends RuntimeException {
    public CuentaNotFoundException(String message) {
        super(message);
    }
}