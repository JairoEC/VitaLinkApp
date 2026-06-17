package cibertec.edu.pe.model.consulta;

import cibertec.edu.pe.model.constantes.TipoDiagnostico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="diagnosticos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="consultaid")
    private Long consultaId;
    private String codigoCIE10;
    private String descripcion;

    @Enumerated(EnumType.STRING)
    private TipoDiagnostico tipoDiagnostico;
}
