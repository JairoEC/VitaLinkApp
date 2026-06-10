package cibertec.edu.pe.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PacienteRequest {

    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String telefono;
    private String correo;
    private String direccion;

}