package cibertec.edu.pe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cibertec.edu.pe.dto.EspecialidadRequest;
import cibertec.edu.pe.dto.EspecialidadResponse;
import cibertec.edu.pe.mapper.EspecialidadMapper;
import cibertec.edu.pe.model.Especialidad;
import cibertec.edu.pe.repository.EspecialidadRepository;
import cibertec.edu.pe.service.EspecialidadService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspecialidadServiceImpl
        implements EspecialidadService {

    private final EspecialidadRepository repository;
    private final EspecialidadMapper mapper;

    @Override
    public EspecialidadResponse registrar(
            EspecialidadRequest request) {

        Especialidad especialidad =
                mapper.toEntity(request);

        return mapper.toResponse(
                repository.save(especialidad)
        );
    }

    @Override
    public List<EspecialidadResponse> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EspecialidadResponse buscarPorId(Long id) {

        Especialidad especialidad =
                repository.findById(id)
                        .orElseThrow();

        return mapper.toResponse(especialidad);
    }

    @Override
    public EspecialidadResponse actualizar(
            Long id,
            EspecialidadRequest request) {

        Especialidad especialidad =
                repository.findById(id)
                        .orElseThrow();

        especialidad.setNombre(
                request.getNombre());

        especialidad.setDescripcion(
                request.getDescripcion());

        return mapper.toResponse(
                repository.save(especialidad)
        );
    }

    @Override
    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}