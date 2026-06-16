package cibertec.edu.pe.mapper;

import cibertec.edu.pe.dto.DisponibilidadResponse;
import cibertec.edu.pe.dto.MedicoResponse;
import cibertec.edu.pe.model.Disponibilidad;
import org.springframework.stereotype.Component;

@Component
public class DisponibilidadMapper {

    public DisponibilidadResponse toResponse(Disponibilidad disponibilidad) {
        MedicoResponse medicoResponse = MedicoResponse.builder()
                .id(disponibilidad.getMedico().getId())
                .nombres(disponibilidad.getMedico().getNombres())
                .apellidos(disponibilidad.getMedico().getApellidos())
                .telefono(disponibilidad.getMedico().getTelefono())
                .correo(disponibilidad.getMedico().getCorreo())
                .especialidad(disponibilidad.getMedico().getEspecialidad().getNombre())
                .build();

        return DisponibilidadResponse.builder()
                .id(disponibilidad.getId())
                .medico(medicoResponse)
                .diaSemana(disponibilidad.getDiaSemana())
                .horaInicio(disponibilidad.getHoraInicio())
                .horaFin(disponibilidad.getHoraFin())
                .build();
    }
}
