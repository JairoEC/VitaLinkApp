package cibertec.edu.pe.dto;

import lombok.Data;

@Data
public class MedicoResponse {
    private Long id;
    private String cmp;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private Boolean estado;
    private EspecialidadResponse especialidad;
}
