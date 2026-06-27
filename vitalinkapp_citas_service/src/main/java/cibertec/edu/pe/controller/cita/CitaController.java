package cibertec.edu.pe.controller.cita;

import cibertec.edu.pe.dto.CitaCreateDto;
import cibertec.edu.pe.dto.CitaResponseDto;
import cibertec.edu.pe.model.cita.Cita;
import cibertec.edu.pe.service.cita.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {

    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> listarTodas() {
        return ResponseEntity.ok(citaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> buscarPorId(@PathVariable("id") Long id) {
        return citaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/dto/{id}")
    public ResponseEntity<CitaResponseDto> buscarCitaDtoPorId(@PathVariable("id") Long id){
        CitaResponseDto citaDto = citaService.buscarCitaDto(id);
        if (citaDto == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        }
        return  ResponseEntity.status(HttpStatus.ACCEPTED)
                .body(citaService.buscarCitaDto(id));

    }

    @PostMapping
    public ResponseEntity<Cita> crear(@RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.guardar(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cita> actualizar(@PathVariable("id") Long id, @RequestBody Cita cita) {
        try {
            return ResponseEntity.ok(citaService.actualizar(id, cita));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    /// ////
    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<List<LocalTime>> obtenerHorariosLibres(@PathVariable("id") Long medicoId,
                                                                 @RequestParam String fecha){
        LocalDate localDate = LocalDate.parse(fecha);
        List<LocalTime> horarios = citaService.obtenerHorariosLibres(medicoId, localDate);
        return ResponseEntity.ok(horarios);
    }
    @PostMapping("/reservar")
    public ResponseEntity<?> reservarCita(@RequestBody CitaCreateDto dto) {
        try {
            Cita citaReservada = citaService.reservarCita(dto);
            return ResponseEntity.ok(citaReservada);
        } catch (RuntimeException e) {
            // Retorna un 400 Bad Request si la validación falla (ej: horario ocupado)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}