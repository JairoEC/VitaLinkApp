package cibertec.edu.pe.service.consulta;

import cibertec.edu.pe.model.consulta.Prescripcion;
import cibertec.edu.pe.repository.consulta.PrescripcionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrescripcionService {

    private final PrescripcionRepository prescripcionRepository;

    public List<Prescripcion> listarTodos() {
        return prescripcionRepository.findAll();
    }

    public Optional<Prescripcion> buscarPorId(Long id) {
        return prescripcionRepository.findById(id);
    }

    public Prescripcion actualizar(Long id, Prescripcion prescripcionActualizada) {
        return prescripcionRepository.findById(id).map(prescripcion -> {
            prescripcion.setMedicamento(prescripcionActualizada.getMedicamento());
            prescripcion.setDosis(prescripcionActualizada.getDosis());
            prescripcion.setFrecuencia(prescripcionActualizada.getFrecuencia());
            prescripcion.setDuracionDias(prescripcionActualizada.getDuracionDias());
            return prescripcionRepository.save(prescripcion);
        }).orElseThrow(() -> new RuntimeException("Prescripción no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        prescripcionRepository.deleteById(id);
    }
}