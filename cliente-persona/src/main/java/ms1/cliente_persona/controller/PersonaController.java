package ms1.cliente_persona.controller;

import lombok.RequiredArgsConstructor;
import ms1.cliente_persona.dto.ClienteDto;
import ms1.cliente_persona.dto.PersonaDto;
import ms1.cliente_persona.service.IPersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

import static ms1.cliente_persona.constant.GlobalConstant.V1_API_VERSION;

@RestController
@RequiredArgsConstructor
@RequestMapping(V1_API_VERSION+"/personas")
public class PersonaController {
    private final IPersonaService personaService;
    @GetMapping("")
    public ResponseEntity<List<PersonaDto>> getPersonas()  {
        return ResponseEntity.ok().body(personaService.getAll());
    }

    @PostMapping("")
    public ResponseEntity<PersonaDto> savePersona( @Validated @RequestBody PersonaDto body) {
        return ResponseEntity.ok().body(personaService.save(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> updatePersona(@PathVariable("id") Long id, @Validated @RequestBody PersonaDto body) {
        return ResponseEntity.ok().body(personaService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable("id") Long id) {
        personaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
