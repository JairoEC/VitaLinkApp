package cibertec.edu.pe.service;

import java.util.List;

import cibertec.edu.pe.dto.EspecialidadRequest;
import cibertec.edu.pe.dto.EspecialidadResponse;

public interface EspecialidadService {

    EspecialidadResponse registrar(
            EspecialidadRequest request);

    List<EspecialidadResponse> listar();

    EspecialidadResponse buscarPorId(Long id);

    EspecialidadResponse actualizar(
            Long id,
            EspecialidadRequest request);

    void eliminar(Long id);
}
