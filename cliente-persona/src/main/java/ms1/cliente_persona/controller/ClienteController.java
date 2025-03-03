package ms1.cliente_persona.controller;

import lombok.RequiredArgsConstructor;
import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.model.Cliente;
import ms1.cliente_persona.service.IClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static ms1.cliente_persona.constant.GlobalConstant.V1_API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_API_VERSION+"/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final IClienteService iClienteService;

    @GetMapping("")
    public ResponseEntity<List<ClienteDto>> getCliente()  {
        return ResponseEntity.ok().body(iClienteService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<ClienteDto> saveCliente (@Validated @RequestBody ClienteDto body) {
        return ResponseEntity.ok().body(iClienteService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("id") Long id, @Validated @RequestBody ClienteDto body) {
        return ResponseEntity.ok().body(iClienteService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable("id") Long id) {
        iClienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<String> getNombreCliente(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(iClienteService.getNombreCliente(id));
    }
}
