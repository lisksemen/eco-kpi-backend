package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotNull;

public record EmergencyDto(
        @NotNull
        Integer peopleCountDead,
        @NotNull
        Integer peopleCountStrongInjury,
        @NotNull
        Integer peopleCountFatalInjury,
        @NotNull
        Integer peopleCountLightInjury,
        @NotNull
        String objectName,
        @NotNull
        Long pollutantId,
        @NotNull
        Double mass,
        @NotNull
        Double concentration
) {

}
