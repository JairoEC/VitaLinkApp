package cibertec.edu.pe.dto;

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
public class CitaCreateDto {
    //paciente
    //medico
    private Long pacienteId;

    private Long medicoId;

    private LocalDateTime fechaHora;
    private String motivo;
    private String estado;
}
