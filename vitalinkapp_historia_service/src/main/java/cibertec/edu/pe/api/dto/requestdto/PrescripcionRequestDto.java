package cibertec.edu.pe.api.dto.requestdto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PrescripcionRequestDto {
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;
}
