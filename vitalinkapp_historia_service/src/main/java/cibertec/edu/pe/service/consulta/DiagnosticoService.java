package cibertec.edu.pe.service.consulta;

import cibertec.edu.pe.model.consulta.Diagnostico;
import cibertec.edu.pe.repository.consulta.DiagnosticoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;

    public List<Diagnostico> listarTodos() {
        return diagnosticoRepository.findAll();
    }

    public Optional<Diagnostico> buscarPorId(Long id) {
        return diagnosticoRepository.findById(id);
    }

    public Diagnostico actualizar(Long id, Diagnostico diagnosticoActualizado) {
        return diagnosticoRepository.findById(id).map(diagnostico -> {
            diagnostico.setDescripcion(diagnosticoActualizado.getDescripcion());
            diagnostico.setMedicoId(diagnosticoActualizado.getMedicoId());
            return diagnosticoRepository.save(diagnostico);
        }).orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}