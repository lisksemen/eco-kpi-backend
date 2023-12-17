package ua.kpi.eco.dto;

import ua.kpi.eco.model.Pollutant;

public record AggregatedPollutionDto(
        Long id,
        String objectName,
        String objectDescription,
        Pollutant pollutant,
        double valuePollution,
        Long pollutantMfr,
        Long pollutantElv,
        double pollutantTlv,
        double pollutionConcentration,
        double hq,
        double cr,
        double fee,
        int year,
        double tax
) {}
