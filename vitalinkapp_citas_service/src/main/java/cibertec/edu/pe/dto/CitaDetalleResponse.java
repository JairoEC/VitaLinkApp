package cibertec.edu.pe.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CitaDetalleResponse {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
    private String especialidad;
    private PacienteResponse paciente;
    private MedicoResponse medico;
}