package com.develop.challengeitaubackend.modules.statistics.dto;

public record StatisticsResponseDTO(
        Long count,
        Double sum,
        Double min,
        Double max,
        Double average
) {}
