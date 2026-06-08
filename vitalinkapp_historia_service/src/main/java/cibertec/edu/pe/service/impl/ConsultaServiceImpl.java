package cibertec.edu.pe.service.impl;

import cibertec.edu.pe.api.request.ConsultaCrearRequestDto;
import cibertec.edu.pe.api.request.ConsultaUpdateRequestDto;
import cibertec.edu.pe.api.response.ConsultaResponseDto;
import cibertec.edu.pe.feign_client.CitasFeignClient;
import cibertec.edu.pe.feign_client.model.Cita;
import cibertec.edu.pe.feign_client.model.Medico;
import cibertec.edu.pe.mapper.ConsultaMapper;
import cibertec.edu.pe.model.Consulta;
import cibertec.edu.pe.repository.ConsultaRepository;
import cibertec.edu.pe.service.ConsultaService;
import feign.FeignException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaServiceImpl implements ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final CitasFeignClient citasFeignClient;
    private final ConsultaMapper consultaMapper;
    @Override
    public ConsultaResponseDto crearConsulta(ConsultaCrearRequestDto dto) {
        try {
            Medico medico = citasFeignClient.getMedicoById(dto.getMedicoId());
            Cita cita = citasFeignClient.getCitaById(dto.getCitaId());
            Consulta nuevaConsulta = Consulta.builder()
                    // HISTORIA CLINICA
                    .medicoId(medico.getId())
                    .citaId(cita.getId())
                    .fechaAtencion(null)
                    .motivoConsulta(dto.getMotivoConsulta())
                    .observaciones(dto.getObservaciones())
                    .fechaCreacion(LocalDateTime.now())
                    .build();
            consultaRepository.save(nuevaConsulta);
            return consultaMapper.toResponseDto(nuevaConsulta);
        } catch (FeignException.NotFound e) {
            throw new NotFoundException("Medico o cita no existen: "+e);
        } catch (FeignException e){
            throw new NotFoundException("Error de comunicacion con el servicio citas: "+ e);
        }
    }

    @Override
    public List<ConsultaResponseDto> getAllConsulta() {
        List<Consulta> consultas = consultaRepository.findAll();
        return consultas.stream().map(consultaMapper::toResponseDto).toList();
    }

    @Override
    public ConsultaResponseDto getConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new NotFoundException("Consulta no encontrada"));
        return consultaMapper.toResponseDto(consulta);
    }

    @Override
    public void deleteConsulta(Long id) {
        Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new NotFoundException("Consulta no encontrada"));
        consultaRepository.delete(consulta);
    }

    @Override
    public ConsultaResponseDto updateConsulta(Long id, ConsultaUpdateRequestDto dto) {
        Consulta consulta = consultaRepository.findById(id).
                orElseThrow(() -> new NotFoundException("Consulta no encontrada"));

        return null;
    }


}
