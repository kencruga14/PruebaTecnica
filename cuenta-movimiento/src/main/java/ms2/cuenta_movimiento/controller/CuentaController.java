package ms2.cuenta_movimiento.controller;

import lombok.RequiredArgsConstructor;
import ms2.cuenta_movimiento.dto.CuentaDto;
import ms2.cuenta_movimiento.service.ICuentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ms2.cuenta_movimiento.constant.GlobalConstant.V1_API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_API_VERSION+"/cuentas")
public class CuentaController {
    private final ICuentaService icuentaService;

    @GetMapping("")
    public ResponseEntity<List<CuentaDto>> getCuentas()  {
        return ResponseEntity.ok().body(icuentaService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<CuentaDto> saveCuenta( @Validated @RequestBody CuentaDto body) {
        return ResponseEntity.ok().body(icuentaService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDto> updateCuenta(@PathVariable("id") Long id, @Validated @RequestBody CuentaDto body) {
        return ResponseEntity.ok().body(icuentaService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable("id") Long id) {
        icuentaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
