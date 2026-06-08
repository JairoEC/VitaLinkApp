package cibertec.edu.pe.feign_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    private Long id;
    private String nombres;
    private String especialidad;
    private LocalDate fechaNacimiento;
}
