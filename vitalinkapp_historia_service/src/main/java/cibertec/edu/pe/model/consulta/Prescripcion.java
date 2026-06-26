package cibertec.edu.pe.model.consulta;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "prescripciones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prescripcion {
    private Long id;
    @OneToOne(mappedBy = "prescripcion_id")
    private Consulta consulta;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;
}
