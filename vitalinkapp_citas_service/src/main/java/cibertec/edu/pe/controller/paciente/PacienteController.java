package cibertec.edu.pe.controller.paciente;

import cibertec.edu.pe.model.paciente.Paciente;
import cibertec.edu.pe.service.paciente.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        return ResponseEntity.ok(pacienteService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPorId(@PathVariable("id") Long id) {
        return pacienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Paciente> crear(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.guardar(paciente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> actualizar(@PathVariable("id") Long id, @RequestBody Paciente paciente) {
        try {
            return ResponseEntity.ok(pacienteService.actualizar(id, paciente));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
