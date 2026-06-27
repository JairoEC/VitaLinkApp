package cibertec.edu.pe.api.controller.consulta;

import cibertec.edu.pe.feign_client.client.ConsultaClient;
import cibertec.edu.pe.feign_client.dto.ConsultaClientDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/historia-clinica")
@RequiredArgsConstructor
public class ConsultaController {
    //
    private final ConsultaClient consultaClient;
    @GetMapping("/{id}")
    public ConsultaClientDto obtenerConsulta(@PathVariable("id") Long id){
        try{
            ConsultaClientDto consultaDto = consultaClient.getConsultaPorId(id);
            log.info("CONSULTA ENCONTRADA: "+consultaDto.toString());
            return consultaDto;
        } catch (FeignException.NotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La cita no esxiste");
        }
    }
}
