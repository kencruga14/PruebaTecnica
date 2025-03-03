package ms2.cuenta_movimiento.controller;

import lombok.RequiredArgsConstructor;
import ms2.cuenta_movimiento.dto.MovimientoDto;
import ms2.cuenta_movimiento.dto.ReporteDto;
import ms2.cuenta_movimiento.service.IReporteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

import static ms2.cuenta_movimiento.constant.GlobalConstant.V1_API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_API_VERSION+"/reportes")
public class ReporteController {
    private final IReporteService iMovimientoService;

    @GetMapping("")
    public ResponseEntity<List<ReporteDto>> getReporte(@RequestParam Date fechaInicio, @RequestParam Date fechaFin, @RequestParam String numeroCuenta)  {
        return ResponseEntity.ok().body(iMovimientoService.getReportByDateAndCuenta(fechaInicio, fechaFin, numeroCuenta));
    }
}
