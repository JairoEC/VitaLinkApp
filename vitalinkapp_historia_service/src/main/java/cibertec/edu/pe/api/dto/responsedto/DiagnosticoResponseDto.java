package cibertec.edu.pe.api.dto.responsedto;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DiagnosticoResponseDto {
    private Long id;
    private String descripcion;
    private Long medicoId;
}