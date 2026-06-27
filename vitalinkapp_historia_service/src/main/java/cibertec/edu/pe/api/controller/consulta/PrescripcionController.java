package cibertec.edu.pe.api.controller.consulta;

import cibertec.edu.pe.model.consulta.Prescripcion;
import cibertec.edu.pe.service.consulta.PrescripcionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescripciones")
@RequiredArgsConstructor
public class PrescripcionController {

    private final PrescripcionService prescripcionService;

    @GetMapping
    public ResponseEntity<List<Prescripcion>> listarTodos() {
        return ResponseEntity.ok(prescripcionService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescripcion> buscarPorId(@PathVariable Long id) {
        return prescripcionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescripcion> actualizar(@PathVariable Long id, @RequestBody Prescripcion prescripcion) {
        try {
            return ResponseEntity.ok(prescripcionService.actualizar(id, prescripcion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        prescripcionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}