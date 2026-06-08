package cibertec.edu.pe.service;

import cibertec.edu.pe.api.request.ConsultaCrearRequestDto;
import cibertec.edu.pe.api.request.ConsultaUpdateRequestDto;
import cibertec.edu.pe.api.response.ConsultaResponseDto;

import java.util.List;

public interface ConsultaService {
    ConsultaResponseDto crearConsulta(ConsultaCrearRequestDto dto);
    List<ConsultaResponseDto> getAllConsulta();
    ConsultaResponseDto getConsulta(Long id);
    void deleteConsulta(Long id);
    ConsultaResponseDto updateConsulta(Long id, ConsultaUpdateRequestDto dto);
}
