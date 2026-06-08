package cibertec.edu.pe.feign_client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cita {
    private Long id;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCita;

}
