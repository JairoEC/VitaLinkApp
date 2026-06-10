package cibertec.edu.pe.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteResponse {

    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
}