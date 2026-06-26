package cibertec.edu.pe.service;

import cibertec.edu.pe.dto.DisponibilidadRequest;
import cibertec.edu.pe.dto.DisponibilidadResponse;

import java.util.List;

public interface DisponibilidadService {

    List<DisponibilidadResponse> listarTodas();

    DisponibilidadResponse buscarPorId(Long id);

    DisponibilidadResponse guardar(DisponibilidadRequest request);

    DisponibilidadResponse actualizar(Long id, DisponibilidadRequest request);

    void eliminar(Long id);
}
