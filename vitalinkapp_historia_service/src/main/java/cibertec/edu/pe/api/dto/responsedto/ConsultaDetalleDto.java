package cibertec.edu.pe.api.dto.responsedto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaDetalleDto {
    private Long id;
    private Long citaId;
    private Long medicoId;
    private Integer pesoKg;
    private Integer alturaCm;
    private LocalDate fechaAtencion;
    private LocalDateTime fechaCreacion;
    private String motivoConsulta;
    private String observaciones;
    private DiagnosticoResponseDto diagnostico;
    private PrescripcionResponseDto prescripcion;
}