package com.chromamon.analysis.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestTransformerAnalysisByIdAndDate {

    private String transformerIdentification;
    private String analysisDate;
}
