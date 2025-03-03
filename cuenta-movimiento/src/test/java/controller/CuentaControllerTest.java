package controller;

import ms2.cuenta_movimiento.controller.CuentaController;
import ms2.cuenta_movimiento.dto.CuentaDto;
import ms2.cuenta_movimiento.service.ICuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CuentaControllerTest {

    @Mock
    private ICuentaService icuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    private LocalValidatorFactoryBean validator;

    @BeforeEach
    void setUp() {
        validator = new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();
    }

    @Test
    void getCuentas() {
        // Arrange
        List<CuentaDto> cuentas = Arrays.asList(
                createCuentaDto(1L, "123456", new BigDecimal("100.00")),
                createCuentaDto(2L, "654321", new BigDecimal("200.00"))
        );
        when(icuentaService.getAll()).thenReturn(cuentas);

        ResponseEntity<List<CuentaDto>> response = cuentaController.getCuentas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cuentas, response.getBody());
    }

    @Test
    void saveCuenta() {
        CuentaDto cuentaDto = createCuentaDto(null, "789012", new BigDecimal("50.00"));
        CuentaDto savedCuentaDto = createCuentaDto(3L, "789012", new BigDecimal("50.00"));
        when(icuentaService.save(cuentaDto)).thenReturn(savedCuentaDto);

        ResponseEntity<CuentaDto> response = cuentaController.saveCuenta(cuentaDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedCuentaDto, response.getBody());
    }

    private CuentaDto createCuentaDto(Long id, String numeroCuenta, BigDecimal saldoInicial) {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setCuentaid(id);
        cuentaDto.setNumerocuenta(numeroCuenta);
        cuentaDto.setSaldoinicial(saldoInicial);
        return cuentaDto;
    }
}
