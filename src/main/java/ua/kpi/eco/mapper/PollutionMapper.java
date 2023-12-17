package ua.kpi.eco.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ua.kpi.eco.dto.PollutionResponseDto;
import ua.kpi.eco.model.Pollution;

@Mapper(componentModel = "spring")
public interface PollutionMapper {

    @Mappings({
            @Mapping(target = "objectName", source = "object.name"),
            @Mapping(target = "objectDescription", source = "object.description"),
    })
    PollutionResponseDto entityToPollutionResponse(Pollution pollution);
}
