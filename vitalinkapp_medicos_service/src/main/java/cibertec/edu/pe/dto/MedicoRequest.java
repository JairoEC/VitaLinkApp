package cibertec.edu.pe.dto;

import lombok.Data;

@Data
public class MedicoRequest {

    private String cmp;

    private String nombres;

    private String apellidos;

    private String telefono;

    private String correo;

    private Long especialidadId;
}
