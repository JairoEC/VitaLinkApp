package cibertec.edu.pe.api.dto.requestdto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConsultaRequestDto {
    private Long citaId;
    private Long medicoId;
    private Long pacienteId;
    private Long historiaClinicaId;
    private Integer pesoKg;
    private Integer alturaCm;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;
    private DiagnosticoRequestDto diagnostico;
    private PrescripcionRequestDto prescripcion;
}