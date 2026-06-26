package cibertec.edu.pe.feign_client.client;

import cibertec.edu.pe.feign_client.dto.ConsultaClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "consulta", url = "http://localhost:8082")
public interface ConsultaClient {
    @GetMapping("/api/citas/{id}")
    ConsultaClientDto getConsultaPorId(@PathVariable("id") Long id);
}
