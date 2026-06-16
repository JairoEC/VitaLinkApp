package cibertec.edu.pe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cibertec.edu.pe.dto.MedicoRequest;
import cibertec.edu.pe.dto.MedicoResponse;
import cibertec.edu.pe.mapper.MedicoMapper;
import cibertec.edu.pe.model.Especialidad;
import cibertec.edu.pe.model.Medico;
import cibertec.edu.pe.repository.EspecialidadRepository;
import cibertec.edu.pe.repository.MedicoRepository;
import cibertec.edu.pe.service.MedicoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicoServiceImpl
        implements MedicoService {

    private final MedicoRepository medicoRepository;
    private final EspecialidadRepository especialidadRepository;
    private final MedicoMapper mapper;

    @Override
    public MedicoResponse registrar(
            MedicoRequest request) {

        Especialidad especialidad =
                especialidadRepository.findById(
                        request.getEspecialidadId())
                .orElseThrow();

        Medico medico = Medico.builder()
                .cmp(request.getCmp())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .estado(true)
                .especialidad(especialidad)
                .build();

        return mapper.toResponse(
                medicoRepository.save(medico)
        );
    }

    @Override
    public List<MedicoResponse> listar() {

        return medicoRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MedicoResponse buscarPorId(Long id) {

        return mapper.toResponse(
                medicoRepository.findById(id)
                        .orElseThrow()
        );
    }

    @Override
    public List<MedicoResponse> buscarPorEspecialidad(
            Long especialidadId) {

        return medicoRepository
                .findByEspecialidadId(especialidadId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MedicoResponse actualizar(
            Long id,
            MedicoRequest request) {

        Medico medico =
                medicoRepository.findById(id)
                        .orElseThrow();

        medico.setNombres(request.getNombres());
        medico.setApellidos(request.getApellidos());
        medico.setTelefono(request.getTelefono());
        medico.setCorreo(request.getCorreo());

        return mapper.toResponse(
                medicoRepository.save(medico)
        );
    }

    @Override
    public void eliminar(Long id) {

        medicoRepository.deleteById(id);
    }
}
