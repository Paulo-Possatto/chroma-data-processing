package com.chromamon.analysis.model.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResultData {

    private String transformerId;
    private String processDate;
    private String result;
    private String method;
    private String analysisDate;
    private String analysisIdentifier;

}
