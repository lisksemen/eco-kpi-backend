package ua.kpi.eco.dto;

import jakarta.validation.constraints.NotEmpty;

public record ObjectDto(
        @NotEmpty
        String name,
        @NotEmpty
        String description
) { }
