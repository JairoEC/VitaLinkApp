package cibertec.edu.pe.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class PacienteResponse {
    private Long id;
    private String dni;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String telefono;
    private String correo;
    private String direccion;
}
