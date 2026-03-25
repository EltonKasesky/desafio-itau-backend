package com.develop.challangeitaubackend.modules.statistics.dto;

import java.util.DoubleSummaryStatistics;

public record StatisticsResponseDTO(DoubleSummaryStatistics statistics) {
}
