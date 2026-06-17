package cibertec.edu.pe.service.impl;

import cibertec.edu.pe.model.consulta.Diagnostico;
import cibertec.edu.pe.repository.DiagnosticoRepository;
import cibertec.edu.pe.service.DiagnosticoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticoServiceImpl implements DiagnosticoService {

    private final DiagnosticoRepository diagnosticoRepository;

    @Override
    public List<Diagnostico> listarTodos() {
        return diagnosticoRepository.findAll();
    }

    @Override
    public Diagnostico buscarPorId(Long id) {
        return diagnosticoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("diagnostico no encontrado"));
    }

    @Override
    public Diagnostico guardar(Diagnostico diagnostico) {
        return diagnosticoRepository.save(diagnostico);
    }

    @Override
    public Diagnostico actualizar(Long id, Diagnostico diagnosticoActualizado) {
        return diagnosticoRepository.findById(id)
                .map(diagnostico -> {
                    diagnostico.setConsultaId(diagnosticoActualizado.getConsultaId());
                    diagnostico.setCodigoCIE10(diagnosticoActualizado.getCodigoCIE10());
                    diagnostico.setDescripcion(diagnosticoActualizado.getDescripcion());
                    diagnostico.setTipoDiagnostico(diagnosticoActualizado.getTipoDiagnostico());

                    return diagnosticoRepository.save(diagnostico);
                })
                .orElseThrow(() -> new RuntimeException("Diagnóstico no encontrado con id: " + id));
    }

    @Override
    public void eliminar(Long id) {
        diagnosticoRepository.deleteById(id);
    }
}
