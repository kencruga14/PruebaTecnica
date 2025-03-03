package ms2.cuenta_movimiento.controller;

import lombok.RequiredArgsConstructor;
import ms2.cuenta_movimiento.dto.MovimientoDto;
import ms2.cuenta_movimiento.service.IMovimientoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static ms2.cuenta_movimiento.constant.GlobalConstant.V1_API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_API_VERSION+"/movimientos")
public class MovimientoController {
    private final IMovimientoService iMovimientoService;

    @GetMapping("")
    public ResponseEntity<List<MovimientoDto>> getMovimientos()  {
        return ResponseEntity.ok().body(iMovimientoService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<MovimientoDto> saveMovimientos( @Validated @RequestBody MovimientoDto body) {
        return ResponseEntity.ok().body(iMovimientoService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDto> updateMovimientos(@PathVariable("id") Long id, @Validated @RequestBody MovimientoDto body) {
        return ResponseEntity.ok().body(iMovimientoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimientos(@PathVariable("id") Long id) {
        iMovimientoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
