package controller;

import ms2.cuenta_movimiento.controller.MovimientoController;
import ms2.cuenta_movimiento.dto.MovimientoDto;
import ms2.cuenta_movimiento.service.IMovimientoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.http.HttpStatus;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MovimientoCotrollerTest {
    @Mock
    private IMovimientoService iMovimientoService;

    @InjectMocks
    private MovimientoController movimientoController;

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
    }

    @Test
    void getMovimientos() {
        List<MovimientoDto> movimientos = Arrays.asList(
                createMovimientoDto(1L, new BigDecimal("100.00"), "DEPOSITO"),
                createMovimientoDto(2L, new BigDecimal("-50.00"), "RETIRO")
        );
        when(iMovimientoService.getAll()).thenReturn(movimientos);

        ResponseEntity<List<MovimientoDto>> response = movimientoController.getMovimientos();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(movimientos, response.getBody());
    }

    @Test
    void saveMovimientos() {
        MovimientoDto movimientoDto = createMovimientoDto(null, new BigDecimal("200.00"), "DEPOSITO");
        MovimientoDto savedMovimientoDto = createMovimientoDto(3L, new BigDecimal("200.00"), "DEPOSITO");
        when(iMovimientoService.save(movimientoDto)).thenReturn(savedMovimientoDto);

        ResponseEntity<MovimientoDto> response = movimientoController.saveMovimientos(movimientoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedMovimientoDto, response.getBody());
    }

    private MovimientoDto createMovimientoDto(Long id, BigDecimal valor, String tipoMovimiento) {
        MovimientoDto movimientoDto = new MovimientoDto();
        movimientoDto.setMovimientoid(id);
        movimientoDto.setValor(valor);
        movimientoDto.setTipomovimiento(tipoMovimiento);
        movimientoDto.setFecha(Date.valueOf(LocalDateTime.now().toLocalDate()));
        return movimientoDto;
    }
}
