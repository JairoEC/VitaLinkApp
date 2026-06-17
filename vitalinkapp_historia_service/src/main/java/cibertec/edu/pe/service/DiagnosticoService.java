package cibertec.edu.pe.service;

import cibertec.edu.pe.model.consulta.Diagnostico;

import java.util.List;

public interface DiagnosticoService {
    List<Diagnostico> listarTodos();
    Diagnostico buscarPorId(Long id);
    Diagnostico guardar(Diagnostico diagnostico);
    Diagnostico actualizar(Long id, Diagnostico diagnosticoActualizado);
    void eliminar(Long id);
}
