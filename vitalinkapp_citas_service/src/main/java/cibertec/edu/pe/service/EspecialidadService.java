package cibertec.edu.pe.service;

import cibertec.edu.pe.model.Especialidad;
import cibertec.edu.pe.repository.EspecialidadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;

    public List<Especialidad> listarTodas() {
        return especialidadRepository.findAll();
    }

    public Optional<Especialidad> buscarPorId(Long id) {
        return especialidadRepository.findById(id);
    }

    public Especialidad guardar(Especialidad especialidad) {
        return especialidadRepository.save(especialidad);
    }

    public Especialidad actualizar(Long id, Especialidad especialidadActualizada) {
        return especialidadRepository.findById(id).map(especialidad -> {
            especialidad.setNombre(especialidadActualizada.getNombre());
            especialidad.setDescripcion(especialidadActualizada.getDescripcion());
            return especialidadRepository.save(especialidad);
        }).orElseThrow(() -> new RuntimeException("Especialidad no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        especialidadRepository.deleteById(id);
    }
}
