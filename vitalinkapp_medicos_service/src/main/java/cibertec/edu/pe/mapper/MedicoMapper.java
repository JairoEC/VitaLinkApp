package cibertec.edu.pe.mapper;

import org.springframework.stereotype.Component;

import cibertec.edu.pe.dto.MedicoResponse;
import cibertec.edu.pe.model.Medico;

@Component
public class MedicoMapper {

    public MedicoResponse toResponse(Medico medico) {

        return MedicoResponse.builder()
                .id(medico.getId())
                .cmp(medico.getCmp())
                .nombres(medico.getNombres())
                .apellidos(medico.getApellidos())
                .telefono(medico.getTelefono())
                .correo(medico.getCorreo())
                .especialidad(
                        medico.getEspecialidad().getNombre()
                )
                .build();
    }
}
