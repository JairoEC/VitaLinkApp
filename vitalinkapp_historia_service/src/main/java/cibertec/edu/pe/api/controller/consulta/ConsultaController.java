package cibertec.edu.pe.api.controller.consulta;

import cibertec.edu.pe.api.dto.requestdto.ConsultaRequestDto;
import cibertec.edu.pe.api.dto.responsedto.HistoriaClinicaResponseDto;
import cibertec.edu.pe.model.consulta.Consulta;
import cibertec.edu.pe.service.consulta.ConsultaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> listarTodos() {
        return ResponseEntity.ok(consultaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarPorId(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/historia/{pacienteId}")
    public ResponseEntity<HistoriaClinicaResponseDto> buscarHistoria(@PathVariable Long pacienteId) {
        try {
            return ResponseEntity.ok(consultaService.buscarHistoriaPorPaciente(pacienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Consulta> crear(@RequestBody ConsultaRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaService.guardar(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> actualizar(@PathVariable Long id, @RequestBody ConsultaRequestDto dto) {
        try {
            return ResponseEntity.ok(consultaService.actualizar(id, dto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        consultaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}