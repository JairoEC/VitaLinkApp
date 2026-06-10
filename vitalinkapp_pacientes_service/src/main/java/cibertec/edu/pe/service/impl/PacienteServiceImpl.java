package cibertec.edu.pe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cibertec.edu.pe.dto.PacienteRequest;
import cibertec.edu.pe.dto.PacienteResponse;
import cibertec.edu.pe.mapper.PacienteMapper;
import cibertec.edu.pe.model.Paciente;
import cibertec.edu.pe.repository.PacienteRepository;
import cibertec.edu.pe.service.PacienteService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository repository;
    private final PacienteMapper mapper;

    @Override
    public PacienteResponse registrar(PacienteRequest request) {

        Paciente paciente = mapper.toEntity(request);

        return mapper.toResponse(
                repository.save(paciente)
        );
    }

    @Override
    public List<PacienteResponse> listar() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public PacienteResponse buscarPorId(Long id) {

        Paciente paciente = repository.findById(id)
                .orElseThrow();

        return mapper.toResponse(paciente);
    }

    @Override
    public PacienteResponse actualizar(Long id,
                                       PacienteRequest request) {

        Paciente paciente = repository.findById(id)
                .orElseThrow();

        paciente.setNombres(request.getNombres());
        paciente.setApellidos(request.getApellidos());
        paciente.setTelefono(request.getTelefono());
        paciente.setCorreo(request.getCorreo());

        return mapper.toResponse(
                repository.save(paciente)
        );
    }

    @Override
    public void eliminar(Long id) {

        repository.deleteById(id);
    }
}
