package cibertec.edu.pe.model.consulta;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToOne(mappedBy = "prescripcion")
    private Consulta consulta;
    private String medicamento;
    private String dosis;
    private String frecuencia;
    private Integer duracionDias;
}
