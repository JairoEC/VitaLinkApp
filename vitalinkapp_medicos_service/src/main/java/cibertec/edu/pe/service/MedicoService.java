package cibertec.edu.pe.service;

import java.util.List;

import cibertec.edu.pe.dto.MedicoRequest;
import cibertec.edu.pe.dto.MedicoResponse;

public interface MedicoService {

    MedicoResponse registrar(
            MedicoRequest request);

    List<MedicoResponse> listar();

    MedicoResponse buscarPorId(Long id);

    List<MedicoResponse> buscarPorEspecialidad(
            Long especialidadId);

    MedicoResponse actualizar(
            Long id,
            MedicoRequest request);

    void eliminar(Long id);
}
