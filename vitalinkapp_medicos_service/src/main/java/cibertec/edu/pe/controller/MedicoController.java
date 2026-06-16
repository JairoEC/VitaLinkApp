package cibertec.edu.pe.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cibertec.edu.pe.dto.MedicoRequest;
import cibertec.edu.pe.dto.MedicoResponse;
import cibertec.edu.pe.service.MedicoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoService service;

    @PostMapping
    public ResponseEntity<MedicoResponse> registrar(
            @RequestBody MedicoRequest request) {

        return ResponseEntity.ok(
                service.registrar(request));
    }

    @GetMapping
    public ResponseEntity<List<MedicoResponse>> listar() {

        return ResponseEntity.ok(
                service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponse> buscar(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @GetMapping("/especialidad/{id}")
    public ResponseEntity<List<MedicoResponse>>
    buscarPorEspecialidad(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorEspecialidad(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponse> actualizar(
            @PathVariable Long id,
            @RequestBody MedicoRequest request) {

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
