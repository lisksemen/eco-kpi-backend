package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotNull;

public record PollutionDto(
        Long id,
        @NotNull
        String objectName,
        String objectDescription,
        @NotNull
        Long pollutantId,
        @NotNull
        int year,
        @NotNull
        double valuePollution,
        @NotNull
        double pollutionConcentration
) {
    public PollutionDto(String objectName, String objectDescription, Long pollutantId,
                        int year, double valuePollution, double pollutionConcentration) {
        this(null, objectName, objectDescription, pollutantId, year, valuePollution, pollutionConcentration);
    }
}
