package com.chromamon.analysis.model.dto;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransformerResultDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String transformerAnalysisId;

    private AnalysisMethodEnum analysisMethodEnum;

    private String analysisResult;

    private String analysisIdentifier;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID resultIdentification;
}
