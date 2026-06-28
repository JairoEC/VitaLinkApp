package cibertec.edu.pe.model.consulta;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "diagnosticos")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicoId;
    private String descripcion;

    @JsonIgnore
    @OneToOne(mappedBy = "diagnostico")
    private Consulta consulta;
}
