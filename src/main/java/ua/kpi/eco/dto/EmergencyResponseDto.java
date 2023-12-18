package ua.kpi.eco.dto;

import ua.kpi.eco.persistence.model.Object;
import ua.kpi.eco.persistence.model.Pollutant;

public record EmergencyResponseDto(
        Long id,
        Integer peopleCountDead,
        Integer peopleCountStrongInjury,
        Integer peopleCountFatalInjury,
        Integer peopleCountLightInjury,
        Object object,
        Pollutant pollutant,
        Double mass,
        Double concentration,
        Double peopleLoss,
        Double emergencyLoss
) {
}
