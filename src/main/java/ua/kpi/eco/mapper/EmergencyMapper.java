package ua.kpi.eco.mapper;

import org.mapstruct.Mapper;
import ua.kpi.eco.dto.EmergencyResponseDto;
import ua.kpi.eco.model.Emergency;

@Mapper(componentModel = "spring")
public interface EmergencyMapper {

    EmergencyResponseDto entityToEmergencyResponseDto (Emergency emergency);
}
