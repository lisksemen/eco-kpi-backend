package ua.kpi.eco.mapper;

import org.mapstruct.Mapper;
import ua.kpi.eco.dto.PollutantDto;
import ua.kpi.eco.dto.PollutantResponseDto;
import ua.kpi.eco.model.Pollutant;

@Mapper(componentModel = "spring")
public interface PollutantMapper {

    PollutantDto pollutantToPollutantDto(Pollutant pollutant);

    PollutantResponseDto pollutantToPollutantResponseDto(Pollutant pollutant);

    Pollutant pollutantDtoToPollutant(PollutantDto pollutantDto);
}
