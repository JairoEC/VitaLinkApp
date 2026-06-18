package cibertec.edu.pe.service.medico;

import cibertec.edu.pe.model.medico.Disponibilidad;
import cibertec.edu.pe.repository.medico.DisponibilidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DisponibilidadService {

    private final DisponibilidadRepository disponibilidadRepository;

    public List<Disponibilidad> listarTodas() {
        return disponibilidadRepository.findAll();
    }

    public Optional<Disponibilidad> buscarPorId(Long id) {
        return disponibilidadRepository.findById(id);
    }

    public Disponibilidad guardar(Disponibilidad disponibilidad) {
        return disponibilidadRepository.save(disponibilidad);
    }

    public Disponibilidad actualizar(Long id, Disponibilidad disponibilidadActualizada) {
        return disponibilidadRepository.findById(id).map(disponibilidad -> {
            disponibilidad.setMedico(disponibilidadActualizada.getMedico());
            disponibilidad.setDiaSemana(disponibilidadActualizada.getDiaSemana());
            disponibilidad.setHoraInicio(disponibilidadActualizada.getHoraInicio());
            disponibilidad.setHoraFin(disponibilidadActualizada.getHoraFin());
            return disponibilidadRepository.save(disponibilidad);
        }).orElseThrow(() -> new RuntimeException("Disponibilidad no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        disponibilidadRepository.deleteById(id);
    }
}
