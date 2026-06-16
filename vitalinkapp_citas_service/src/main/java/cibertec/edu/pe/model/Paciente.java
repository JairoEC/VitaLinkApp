package cibertec.edu.pe.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private String nombres;

    private String apellidos;

    private LocalDate fechaNacimiento;

    private String sexo;

    private String telefono;

    private String correo;

    private String direccion;
}
