package cibertec.edu.pe.soap.endpoint;

import cibertec.edu.pe.service.cita.CitaService;
import cibertec.edu.pe.soap.response.ListarCitasResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CitaSoapEndpoint {

    private static final String NAMESPACE_URI = "http://cibertec.pe/citas";

    private final CitaService citaService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListarCitasRequest")
    @ResponsePayload
    public ListarCitasResponse listarCitas() {

        ListarCitasResponse response = new ListarCitasResponse();
        response.setCitas(citaService.listarTodasDto());

        return response;
    }
}
