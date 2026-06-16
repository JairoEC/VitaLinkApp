package cibertec.edu.pe.controller;

import cibertec.edu.pe.dto.DisponibilidadRequest;
import cibertec.edu.pe.dto.DisponibilidadResponse;
import cibertec.edu.pe.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disponibilidades")
@RequiredArgsConstructor
public class DisponibilidadController {

    private final DisponibilidadService service;

    @PostMapping
    public ResponseEntity<DisponibilidadResponse> registrar(
            @RequestBody DisponibilidadRequest request) {

        return ResponseEntity.ok(
                service.guardar(request));
    }

    @GetMapping
    public ResponseEntity<List<DisponibilidadResponse>> listar() {

        return ResponseEntity.ok(
                service.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadResponse> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisponibilidadResponse> actualizar(
            @PathVariable Long id,
            @RequestBody DisponibilidadRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}