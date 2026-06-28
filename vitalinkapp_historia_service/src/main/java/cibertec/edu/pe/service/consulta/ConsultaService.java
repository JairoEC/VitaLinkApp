package cibertec.edu.pe.service.consulta;

import cibertec.edu.pe.api.dto.requestdto.ConsultaRequestDto;
import cibertec.edu.pe.api.dto.responsedto.*;
import cibertec.edu.pe.feign_client.client.ConsultaClient;
import cibertec.edu.pe.feign_client.client.PacienteClient;
import cibertec.edu.pe.feign_client.dto.ConsultaClientDto;
import cibertec.edu.pe.feign_client.dto.PacienteDto;
import cibertec.edu.pe.model.consulta.*;
import cibertec.edu.pe.model.paciente_clinico.PacienteClinico;
import cibertec.edu.pe.repository.consulta.ConsultaRepository;
import cibertec.edu.pe.repository.paciente_clinico.PacienteClinicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final PacienteClinicoRepository pacienteClinicoRepository;
    private final PacienteClient pacienteClient;
    private final ConsultaClient consultaClient;

    public List<Consulta> listarTodos() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public HistoriaClinicaResponseDto buscarHistoriaPorPaciente(Long pacienteId) {
        // validacion de paciente existente en citas-service
        PacienteDto pacienteDto = pacienteClient.getPacientePorId(pacienteId);

        PacienteClinico pacienteClinico = pacienteClinicoRepository
                .findByPacienteId(pacienteId)
                .orElseThrow(() -> new RuntimeException(
                        "Historia clinica para el paciente no encontrada: " + pacienteId));

        List<ConsultaDetalleDto> consultas = consultaRepository
                .findByPacienteClinico_PacienteIdOrderByFechaAtencionDesc(pacienteId)
                .stream()
                .map(this::mapToDetalleDto)
                .collect(Collectors.toList());

        return HistoriaClinicaResponseDto.builder()
                .pacienteId(pacienteId)
                .nombres(pacienteDto.getNombres())
                .apellidos(pacienteDto.getApellidos())
                .dni(pacienteDto.getDni())
                .ultimoPesoKg(pacienteClinico.getPesoKg())
                .ultimaAlturaCm(pacienteClinico.getAlturaCm())
                .fechaUltimaActualizacion(pacienteClinico.getFechaActualizacion())
                .consultas(consultas)
                .build();
    }

    public Consulta guardar(ConsultaRequestDto dto) {
        PacienteDto pacienteDto = pacienteClient.getPacientePorId(dto.getPacienteId());

        ConsultaClientDto citaDto = consultaClient.getConsultaPorId(dto.getCitaId());

        PacienteClinico pacienteClinico = pacienteClinicoRepository
                .findByPacienteId(dto.getPacienteId())
                .orElseGet(() -> PacienteClinico.builder()
                        .pacienteId(dto.getPacienteId())
                        .build());

        pacienteClinico.setAlturaCm(dto.getAlturaCm());
        pacienteClinico.setPesoKg(dto.getPesoKg());
        pacienteClinico.setFechaActualizacion(LocalDateTime.now());
        pacienteClinicoRepository.save(pacienteClinico);

        Diagnostico diagnostico = Diagnostico.builder()
                .medicoId(dto.getMedicoId())
                .descripcion(dto.getDiagnostico().getDescripcion())
                .build();

        Prescripcion prescripcion = Prescripcion.builder()
                .medicamento(dto.getPrescripcion().getMedicamento())
                .dosis(dto.getPrescripcion().getDosis())
                .frecuencia(dto.getPrescripcion().getFrecuencia())
                .duracionDias(dto.getPrescripcion().getDuracionDias())
                .build();

        Consulta consulta = Consulta.builder()
                .citaId(dto.getCitaId())
                .medicoId(dto.getMedicoId())
                .historiaClinicaId(dto.getHistoriaClinicaId())
                .pacienteClinico(pacienteClinico)
                .pesoKg(dto.getPesoKg())
                .alturaCm(dto.getAlturaCm())
                .fechaAtencion(dto.getFechaAtencion())
                .motivoConsulta(dto.getMotivoConsulta())
                .observaciones(dto.getObservaciones())
                .diagnostico(diagnostico)
                .prescripcion(prescripcion)
                .fechaCreacion(LocalDateTime.now())
                .build();

        return consultaRepository.save(consulta);
    }

    public Consulta actualizar(Long id, ConsultaRequestDto dto) {
        return consultaRepository.findById(id).map(consulta -> {
            consulta.setPesoKg(dto.getPesoKg());
            consulta.setAlturaCm(dto.getAlturaCm());
            consulta.setFechaAtencion(dto.getFechaAtencion());
            consulta.setMotivoConsulta(dto.getMotivoConsulta());
            consulta.setObservaciones(dto.getObservaciones());

            if (dto.getDiagnostico() != null && consulta.getDiagnostico() != null) {
                consulta.getDiagnostico().setDescripcion(dto.getDiagnostico().getDescripcion());
            }
            if (dto.getPrescripcion() != null && consulta.getPrescripcion() != null) {
                consulta.getPrescripcion().setMedicamento(dto.getPrescripcion().getMedicamento());
                consulta.getPrescripcion().setDosis(dto.getPrescripcion().getDosis());
                consulta.getPrescripcion().setFrecuencia(dto.getPrescripcion().getFrecuencia());
                consulta.getPrescripcion().setDuracionDias(dto.getPrescripcion().getDuracionDias());
            }
            return consultaRepository.save(consulta);
        }).orElseThrow(() -> new RuntimeException("Consulta no encontrada con id: " + id));
    }

    public void eliminar(Long id) {
        consultaRepository.deleteById(id);
    }

    private ConsultaDetalleDto mapToDetalleDto(Consulta c) {
        return ConsultaDetalleDto.builder()
                .id(c.getId())
                .citaId(c.getCitaId())
                .medicoId(c.getMedicoId())
                .pesoKg(c.getPesoKg())
                .alturaCm(c.getAlturaCm())
                .fechaAtencion(c.getFechaAtencion())
                .fechaCreacion(c.getFechaCreacion())
                .motivoConsulta(c.getMotivoConsulta())
                .observaciones(c.getObservaciones())
                .diagnostico(c.getDiagnostico() != null ? DiagnosticoResponseDto.builder()
                        .id(c.getDiagnostico().getId())
                        .descripcion(c.getDiagnostico().getDescripcion())
                        .medicoId(c.getDiagnostico().getMedicoId())
                        .build() : null)
                .prescripcion(c.getPrescripcion() != null ? PrescripcionResponseDto.builder()
                        .id(c.getPrescripcion().getId())
                        .medicamento(c.getPrescripcion().getMedicamento())
                        .dosis(c.getPrescripcion().getDosis())
                        .frecuencia(c.getPrescripcion().getFrecuencia())
                        .duracionDias(c.getPrescripcion().getDuracionDias())
                        .build() : null)
                .build();
    }
}