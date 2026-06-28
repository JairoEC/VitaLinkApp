package cibertec.edu.pe.feign_client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaClientDto {
    private Long id;
    private String estado;
    private String motivo;
    private LocalDateTime fechaHora;
    private String nombrePaciente;
    private String correoPaciente;
    private String dniPaciente;
    private LocalDate fechaNacimiento;
    private String nombreMedico;
    private String apellidoMedico;
    private String especialidad;
}
