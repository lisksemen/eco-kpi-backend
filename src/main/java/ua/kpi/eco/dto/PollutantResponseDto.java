package ua.kpi.eco.dto;

import ua.kpi.eco.model.PollutantType;

public record PollutantResponseDto(
        Long id,
        String name,
        Long elv,
        Long mfr,
        double tlv,
        double sf,
        double rfc,
        PollutantType pollutantType,
        double taxRate
) {}
