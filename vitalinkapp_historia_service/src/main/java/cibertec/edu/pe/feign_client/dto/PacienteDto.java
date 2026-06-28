package cibertec.edu.pe.feign_client.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PacienteDto {
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
