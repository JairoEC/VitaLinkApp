package cibertec.edu.pe.controller.medico;

import cibertec.edu.pe.model.medico.Disponibilidad;
import cibertec.edu.pe.service.medico.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadController {

    private final DisponibilidadService disponibilidadService;

    @GetMapping
    public ResponseEntity<List<Disponibilidad>> listarTodas() {
        return ResponseEntity.ok(disponibilidadService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disponibilidad> buscarPorId(@PathVariable Long id) {
        return disponibilidadService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Disponibilidad> crear(@RequestBody Disponibilidad disponibilidad) {
        return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadService.guardar(disponibilidad));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disponibilidad> actualizar(@PathVariable Long id, @RequestBody Disponibilidad disponibilidad) {
        try {
            return ResponseEntity.ok(disponibilidadService.actualizar(id, disponibilidad));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        disponibilidadService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}