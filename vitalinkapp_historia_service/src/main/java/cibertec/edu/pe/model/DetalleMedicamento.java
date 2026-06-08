package cibertec.edu.pe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_medicamento")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetalleMedicamento {
    @Id
    private Long id;
    private String medicamento;
    private Double dosis;
    private String unidadMedida;// mg, ml, mm, cm3, mm3, Kg, L
    private String frecuencia;

    @ManyToOne
    @JoinColumn(name = "prescripcion_id")
    private Prescripcion prescripcion;

}
