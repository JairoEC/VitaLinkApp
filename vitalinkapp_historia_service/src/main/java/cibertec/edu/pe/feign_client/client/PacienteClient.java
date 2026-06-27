package cibertec.edu.pe.feign_client.client;

import cibertec.edu.pe.feign_client.dto.PacienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paciente-service", url = "http://localhost:8082")
public interface PacienteClient {
    @GetMapping("/api/pacientes/{id}")
    PacienteDto getPacientePorId(@PathVariable("id") Long id);
}