package cibertec.edu.pe.api.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaUpdateRequestDto {
    private Long id;
    private Long medicoId;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;
}
