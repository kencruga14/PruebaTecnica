package ms2.cuenta_movimiento.handler;

import ms2.cuenta_movimiento.exception.generico.ClienteNotFoundException;
import ms2.cuenta_movimiento.exception.cuenta.CuentaAlreadyExistsException;
import ms2.cuenta_movimiento.exception.cuenta.CuentaNotFoundException;
import ms2.cuenta_movimiento.exception.generico.DatabaseOperationException;
import ms2.cuenta_movimiento.exception.generico.SaldoInsuficienteException;
import ms2.cuenta_movimiento.exception.movimiento.MovimientoNotFoundException;
import ms2.cuenta_movimiento.exception.movimiento.TipoMovimientoInvalidoException;
import ms2.cuenta_movimiento.exception.reporte.ReporteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CuentaNotFoundException.class)
    public ResponseEntity<String> handleCuentaNotFoundException(CuentaNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CuentaAlreadyExistsException.class)
    public ResponseEntity<String> handleCuentaAlreadyExistsException(CuentaAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<String> handleClienteNotFoundException(ClienteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ReporteNotFoundException.class)
    public ResponseEntity<String> handleReporteNotFoundException(ReporteNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<String> handleSaldoInsuficienteException(SaldoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(TipoMovimientoInvalidoException.class)
    public ResponseEntity<String> handleTipoMovimientoInvalidoException(TipoMovimientoInvalidoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(MovimientoNotFoundException.class)
    public ResponseEntity<String> handleMovimientoNotFundException(MovimientoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DatabaseOperationException.class)
    public ResponseEntity<String> handleDatabaseOperationException(DatabaseOperationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error de base de datos: " + ex.getMessage());
    }


}
