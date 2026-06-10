package cibertec.edu.pe.mapper;

import org.springframework.stereotype.Component;

import cibertec.edu.pe.dto.PacienteRequest;
import cibertec.edu.pe.dto.PacienteResponse;
import cibertec.edu.pe.model.Paciente;

@Component
public class PacienteMapper {

    public Paciente toEntity(PacienteRequest request) {

        return Paciente.builder()
                .dni(request.getDni())
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .fechaNacimiento(request.getFechaNacimiento())
                .sexo(request.getSexo())
                .telefono(request.getTelefono())
                .correo(request.getCorreo())
                .direccion(request.getDireccion())
                .estado(true)
                .build();
    }

    public PacienteResponse toResponse(Paciente paciente) {

        return PacienteResponse.builder()
                .id(paciente.getId())
                .dni(paciente.getDni())
                .nombres(paciente.getNombres())
                .apellidos(paciente.getApellidos())
                .telefono(paciente.getTelefono())
                .correo(paciente.getCorreo())
                .build();
    }
}