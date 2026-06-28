package cibertec.edu.pe.api.dto.responsedto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoriaClinicaResponseDto {
    private Long pacienteId;
    private String nombres;
    private String apellidos;
    private String dni;
    private Integer ultimoPesoKg;
    private Integer ultimaAlturaCm;
    private LocalDateTime fechaUltimaActualizacion;
    private List<ConsultaDetalleDto> consultas;
}