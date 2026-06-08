package cibertec.edu.pe.api.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ConsultaCrearRequestDto {
    private Long historiaClinicaId;
    private Long medicoId;
    private Long citaId;
    private LocalDate fechaAtencion;
    private String motivoConsulta;
    private String observaciones;

}
