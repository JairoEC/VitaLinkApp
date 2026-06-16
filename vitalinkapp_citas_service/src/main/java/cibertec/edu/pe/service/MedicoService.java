package cibertec.edu.pe.service;

import cibertec.edu.pe.model.Medico;
import cibertec.edu.pe.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    public Optional<Medico> buscarPorId(Long id) {
        return medicoRepository.findById(id);
    }

    public Medico guardar(Medico medico) {
        return medicoRepository.save(medico);
    }

    public Medico actualizar(Long id, Medico medicoActualizado) {
        return medicoRepository.findById(id).map(medico -> {
            medico.setNombres(medicoActualizado.getNombres());
            medico.setApellidos(medicoActualizado.getApellidos());
            medico.setTelefono(medicoActualizado.getTelefono());
            medico.setCorreo(medicoActualizado.getCorreo());
            medico.setEspecialidad(medicoActualizado.getEspecialidad());
            return medicoRepository.save(medico);
        }).orElseThrow(() -> new RuntimeException("Médico no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}
