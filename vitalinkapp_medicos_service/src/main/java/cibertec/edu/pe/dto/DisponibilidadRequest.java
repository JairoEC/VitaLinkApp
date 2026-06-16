package cibertec.edu.pe.dto;

import lombok.Data;
import java.time.LocalTime;

@Data
public class DisponibilidadRequest {
    private Long medicoId;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}
