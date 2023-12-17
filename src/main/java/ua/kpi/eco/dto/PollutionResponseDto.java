package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import ua.kpi.eco.model.Pollutant;

public record PollutionResponseDto(
        Long id,
        @NotEmpty
        String objectName,
        @NotEmpty
        String objectDescription,
        @NotEmpty
        Pollutant pollutant,
        @NotEmpty
        int year,
        @NotEmpty
        double valuePollution,
        @NotEmpty
        double pollutionConcentration,
        @NotEmpty
        double hq,
        @NotEmpty
        double cr,
        @NotEmpty
        double fee,
        @NotNull
        double tax
) {
    public PollutionResponseDto(String objectName, String objectDescription, Pollutant pollutant,
                        int year, double valuePollution, double pollutionConcentration,double hq,double cr,double fee,double tax) {
        this(null, objectName, objectDescription, pollutant, year, valuePollution, pollutionConcentration,hq,cr,fee,tax);
    }
}
