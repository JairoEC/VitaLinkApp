package cibertec.edu.pe.service;

import cibertec.edu.pe.client.MedicoClient;
import cibertec.edu.pe.client.PacienteClient;
import cibertec.edu.pe.dto.CitaDetalleResponse;
import cibertec.edu.pe.dto.MedicoResponse;
import cibertec.edu.pe.dto.PacienteResponse;
import cibertec.edu.pe.model.Cita;
import cibertec.edu.pe.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final PacienteClient pacienteClient;
    private final MedicoClient medicoClient;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita citaActualizada) {
        return citaRepository.findById(id).map(cita -> {
            cita.setPacienteId(citaActualizada.getPacienteId());
            cita.setMedicoId(citaActualizada.getMedicoId());
            cita.setEspecialidad(citaActualizada.getEspecialidad());
            cita.setFechaHora(citaActualizada.getFechaHora());
            cita.setMotivo(citaActualizada.getMotivo());
            cita.setEstado(citaActualizada.getEstado());
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }

    public CitaDetalleResponse obtenerDetalle(Long id) {
        Cita cita = citaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));

        PacienteResponse paciente = pacienteClient.obtenerPorId(cita.getPacienteId());
        MedicoResponse medico = medicoClient.obtenerPorId(cita.getMedicoId());

        CitaDetalleResponse detalle = new CitaDetalleResponse();
        detalle.setId(cita.getId());
        detalle.setFechaHora(cita.getFechaHora());
        detalle.setMotivo(cita.getMotivo());
        detalle.setEstado(cita.getEstado());
        detalle.setEspecialidad(cita.getEspecialidad());
        detalle.setPaciente(paciente);
        detalle.setMedico(medico);

        return detalle;
    }
}