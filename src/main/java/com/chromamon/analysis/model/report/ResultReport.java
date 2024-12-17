package com.chromamon.analysis.model.report;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@Builder
@ToString
public class ResultReport {
    private AnalysisMethodEnum analysisMethod;
    private String analysisResult;
    private UUID resultIdentification;
}
