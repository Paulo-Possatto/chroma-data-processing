package com.chromamon.analysis.model.report;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
public class AnalysisReport {
    private LocalDateTime analysisTimestamp;
    private Integer c2h2Ppm;
    private Integer c2h4Ppm;
    private Integer c2h6Ppm;
    private Integer ch4Ppm;
    private Integer coPpm;
    private Integer co2Ppm;
    private Integer h2Ppm;
    private Double oilAcidity;
    private Double oilPressure;
    private Integer oilTemperature;

    private List<ResultReport> results;
}
