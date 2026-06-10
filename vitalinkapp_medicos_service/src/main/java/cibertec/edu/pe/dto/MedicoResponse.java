package cibertec.edu.pe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoResponse {

    private Long id;

    private String cmp;

    private String nombres;

    private String apellidos;

    private String telefono;

    private String correo;

    private String especialidad;
}