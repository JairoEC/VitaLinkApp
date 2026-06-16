package cibertec.edu.pe.service;

import java.util.List;

import cibertec.edu.pe.dto.PacienteRequest;
import cibertec.edu.pe.dto.PacienteResponse;

public interface PacienteService {

    PacienteResponse registrar(PacienteRequest request);

    List<PacienteResponse> listar();

    PacienteResponse buscarPorId(Long id);

    PacienteResponse actualizar(Long id,
                                PacienteRequest request);

    void eliminar(Long id);
}
