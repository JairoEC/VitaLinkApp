package cibertec.edu.pe.mapper;

import org.springframework.stereotype.Component;

import cibertec.edu.pe.dto.EspecialidadRequest;
import cibertec.edu.pe.dto.EspecialidadResponse;
import cibertec.edu.pe.model.Especialidad;

@Component
public class EspecialidadMapper {

    public Especialidad toEntity(
            EspecialidadRequest request) {

        return Especialidad.builder()
                .nombre(request.getNombre())
                .descripcion(request.getDescripcion())
                .build();
    }

    public EspecialidadResponse toResponse(
            Especialidad especialidad) {

        return EspecialidadResponse.builder()
                .id(especialidad.getId())
                .nombre(especialidad.getNombre())
                .descripcion(especialidad.getDescripcion())
                .build();
    }
}
