package cibertec.edu.pe.api.controller.consulta;

import cibertec.edu.pe.model.consulta.Diagnostico;
import cibertec.edu.pe.service.consulta.DiagnosticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnosticos")
@RequiredArgsConstructor
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    @GetMapping
    public ResponseEntity<List<Diagnostico>> listarTodos() {
        return ResponseEntity.ok(diagnosticoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> buscarPorId(@PathVariable Long id) {
        return diagnosticoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> actualizar(@PathVariable Long id, @RequestBody Diagnostico diagnostico) {
        try {
            return ResponseEntity.ok(diagnosticoService.actualizar(id, diagnostico));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        diagnosticoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
