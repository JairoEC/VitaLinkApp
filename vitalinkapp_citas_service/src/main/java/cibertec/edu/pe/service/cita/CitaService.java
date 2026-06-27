package cibertec.edu.pe.service.cita;


import cibertec.edu.pe.dto.CitaResponseDto;
import cibertec.edu.pe.model.cita.Cita;
import cibertec.edu.pe.model.medico.Medico;
import cibertec.edu.pe.repository.cita.CitaRepository;
import cibertec.edu.pe.repository.medico.MedicoRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;

    public List<Cita> listarTodas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> buscarPorId(Long id) {
        return citaRepository.findById(id);
    }

    public CitaResponseDto buscarCitaDto(Long id){
        Cita cita = citaRepository.findById(id).orElseThrow(
                ()->new NotFoundException("CITA NO ENCONTRADA"));
        log.info("CITA : "+cita.getId());
        Medico medico = medicoRepository.findById(cita.getMedico().getId())
                .orElseThrow(()-> new NotFoundException("MEDICO NO ENCONTRADO"));
        log.info("MEDICO: "+medico.getId());
        log.info("ESPECIALIDAD: "+medico.getEspecialidad().getNombre());
        CitaResponseDto citaResponseDto = CitaResponseDto.builder()
                .id(cita.getId())
                .estado(cita.getEstado())
                .motivo(cita.getMotivo())
                .fechaHora(cita.getFechaHora())
                .nombrePaciente(cita.getPaciente().getNombres())
                .correoPaciente(cita.getPaciente().getCorreo())
                .dniPaciente(cita.getPaciente().getDni())
                .fechaNacimiento(cita.getPaciente().getFechaNacimiento())
                .nombreMedico(medico.getNombres())
                .apellidoMedico(medico.getApellidos())
                .especialidad(medico.getEspecialidad().getNombre())
                .build();
        return citaResponseDto;
    }

    public Cita guardar(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita actualizar(Long id, Cita citaActualizada) {
        return citaRepository.findById(id).map(cita -> {
            cita.setPaciente(citaActualizada.getPaciente());
            cita.setMedico(citaActualizada.getMedico());
            cita.setFechaHora(citaActualizada.getFechaHora());
            cita.setMotivo(citaActualizada.getMotivo());
            cita.setEstado(citaActualizada.getEstado());
            return citaRepository.save(cita);
        }).orElseThrow(() -> new RuntimeException("Cita no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        citaRepository.deleteById(id);
    }
}