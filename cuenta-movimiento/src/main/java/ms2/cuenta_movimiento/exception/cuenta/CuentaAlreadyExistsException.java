package ms2.cuenta_movimiento.exception.cuenta;

public class CuentaAlreadyExistsException extends RuntimeException {
    public CuentaAlreadyExistsException(String message) {
        super(message);
    }
}
