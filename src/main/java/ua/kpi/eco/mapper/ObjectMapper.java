package ua.kpi.eco.mapper;

import org.mapstruct.Mapper;
import ua.kpi.eco.dto.ObjectDto;
import ua.kpi.eco.dto.ObjectResponseDto;
import ua.kpi.eco.model.Object;

@Mapper(componentModel = "spring")
public interface ObjectMapper {

    ObjectDto objectToObjectDto(Object object);

    Object objectDtoToObject(ObjectDto objectDto);

    ObjectResponseDto objectToObjectResponseDto(Object object);
}
