package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotNull;

public record PollutantDto (
    @NotNull
    String name,
    @NotNull
    Long elv,
    @NotNull
    Long mfr,
    @NotNull
    double tlv,
    @NotNull
    double sf,
    @NotNull
    double rfc,
    @NotNull
    Long pollutantTypeId,
    @NotNull
    double taxRate
) {}
