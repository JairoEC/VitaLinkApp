package cibertec.edu.pe.client;

import cibertec.edu.pe.dto.PacienteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vitalinkapp-pacientes")
public interface PacienteClient {

    @GetMapping("/api/pacientes/{id}")
    PacienteResponse obtenerPorId(@PathVariable Long id);
}
