package cibertec.edu.pe.controller.consulta;

import cibertec.edu.pe.feign_client.client.ConsultaClient;
import cibertec.edu.pe.feign_client.dto.ConsultaDto;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/historia-clinica")
@RequiredArgsConstructor
public class ConsultaController {
    //
    private final ConsultaClient consultaClient;
    @GetMapping("/{id}")
    public ConsultaDto obtenerConsulta(@PathVariable("id") Long id){
        try{
            ConsultaDto consultaDto = consultaClient.getConsultaPorId(id);
            return consultaDto;
        } catch (FeignException.NotFound e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La cita no esxiste");
        }
    }
}
