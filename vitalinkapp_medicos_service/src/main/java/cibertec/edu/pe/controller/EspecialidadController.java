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

import cibertec.edu.pe.dto.EspecialidadRequest;
import cibertec.edu.pe.dto.EspecialidadResponse;
import cibertec.edu.pe.service.EspecialidadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/especialidades")
@RequiredArgsConstructor
public class EspecialidadController {

    private final EspecialidadService service;

    @PostMapping
    public ResponseEntity<EspecialidadResponse>
    registrar(
            @RequestBody EspecialidadRequest request) {

        return ResponseEntity.ok(
                service.registrar(request));
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadResponse>>
    listar() {

        return ResponseEntity.ok(
                service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResponse>
    buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspecialidadResponse>
    actualizar(
            @PathVariable Long id,
            @RequestBody EspecialidadRequest request) {

        return ResponseEntity.ok(
                service.actualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>
    eliminar(
            @PathVariable Long id) {

        service.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}
