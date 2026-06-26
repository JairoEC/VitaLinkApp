package cibertec.edu.pe.service.impl;

import cibertec.edu.pe.dto.DisponibilidadRequest;
import cibertec.edu.pe.dto.DisponibilidadResponse;
import cibertec.edu.pe.mapper.DisponibilidadMapper;
import cibertec.edu.pe.model.Disponibilidad;
import cibertec.edu.pe.model.Medico;
import cibertec.edu.pe.repository.DisponibilidadRepository;
import cibertec.edu.pe.repository.MedicoRepository;
import cibertec.edu.pe.service.DisponibilidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisponibilidadServiceImpl implements DisponibilidadService {

    private final DisponibilidadRepository disponibilidadRepository;
    private final MedicoRepository medicoRepository;
    private final DisponibilidadMapper mapper;

    @Override
    public List<DisponibilidadResponse> listarTodas() {
        return disponibilidadRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public DisponibilidadResponse buscarPorId(Long id) {
        return mapper.toResponse(
                disponibilidadRepository.findById(id)
                        .orElseThrow()
        );
    }

    @Override
    public DisponibilidadResponse guardar(DisponibilidadRequest request) {
        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow();

        Disponibilidad disponibilidad = Disponibilidad.builder()
                .medico(medico)
                .diaSemana(request.getDiaSemana())
                .horaInicio(request.getHoraInicio())
                .horaFin(request.getHoraFin())
                .build();

        return mapper.toResponse(
                disponibilidadRepository.save(disponibilidad)
        );
    }

    @Override
    public DisponibilidadResponse actualizar(Long id, DisponibilidadRequest request) {
        Disponibilidad disponibilidad = disponibilidadRepository.findById(id)
                .orElseThrow();

        Medico medico = medicoRepository.findById(request.getMedicoId())
                .orElseThrow();

        disponibilidad.setMedico(medico);
        disponibilidad.setDiaSemana(request.getDiaSemana());
        disponibilidad.setHoraInicio(request.getHoraInicio());
        disponibilidad.setHoraFin(request.getHoraFin());

        return mapper.toResponse(
                disponibilidadRepository.save(disponibilidad)
        );
    }

    @Override
    public void eliminar(Long id) {
        disponibilidadRepository.deleteById(id);
    }
}
