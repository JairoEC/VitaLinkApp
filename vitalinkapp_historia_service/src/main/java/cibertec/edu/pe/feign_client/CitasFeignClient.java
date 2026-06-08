package cibertec.edu.pe.feign_client;

import cibertec.edu.pe.feign_client.model.Cita;
import cibertec.edu.pe.feign_client.model.Medico;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "vitalinkapp-citas")
public interface CitasFeignClient {
    @GetMapping("/medicos/{id}")
    Medico getMedicoById(@PathVariable("id") Long id);
    @GetMapping("/medicos")
    List<Medico> getMedicos();
    @GetMapping("/citas/{id}")
    Cita getCitaById(@PathVariable("id") Long id);
}
