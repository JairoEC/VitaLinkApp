package cibertec.edu.pe.api.dto.responsedto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescripcionResponseDto {
    private Long id;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;
}