package cibertec.edu.pe.mapper;

import cibertec.edu.pe.api.request.ConsultaCrearRequestDto;
import cibertec.edu.pe.api.request.ConsultaUpdateRequestDto;
import cibertec.edu.pe.api.response.ConsultaResponseDto;
import cibertec.edu.pe.model.Consulta;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ConsultaMapper {

    Consulta toEntity(ConsultaCrearRequestDto dto);

    ConsultaResponseDto toResponseDto(Consulta entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ConsultaUpdateRequestDto dto, @MappingTarget Consulta entity);
}
