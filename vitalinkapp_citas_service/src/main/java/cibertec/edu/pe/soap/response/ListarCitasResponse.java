package cibertec.edu.pe.soap.response;

import cibertec.edu.pe.dto.CitaResponseDto;
import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@XmlRootElement(name = "ListarCitasResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListarCitasResponse {

    @XmlElement(name = "cita")
    private List<CitaResponseDto> citas;

}