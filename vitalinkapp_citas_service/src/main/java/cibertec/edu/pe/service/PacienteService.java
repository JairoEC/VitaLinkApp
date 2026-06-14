package cibertec.edu.pe.service;

import cibertec.edu.pe.model.Paciente;
import cibertec.edu.pe.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return pacienteRepository.findById(id);
    }

    public Paciente guardar(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Paciente actualizar(Long id, Paciente pacienteActualizado) {
        return pacienteRepository.findById(id).map(paciente -> {
            paciente.setDni(pacienteActualizado.getDni());
            paciente.setNombres(pacienteActualizado.getNombres());
            paciente.setApellidos(pacienteActualizado.getApellidos());
            paciente.setFechaNacimiento(pacienteActualizado.getFechaNacimiento());
            paciente.setSexo(pacienteActualizado.getSexo());
            paciente.setTelefono(pacienteActualizado.getTelefono());
            paciente.setCorreo(pacienteActualizado.getCorreo());
            paciente.setDireccion(pacienteActualizado.getDireccion());
            return pacienteRepository.save(paciente);
        }).orElseThrow(() -> new RuntimeException("Paciente no encontrado con id: " + id));
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
