package cibertec.edu.pe.dto;

import lombok.*;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DisponibilidadResponse {
    private Long id;
    private MedicoResponse medico;
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;
}