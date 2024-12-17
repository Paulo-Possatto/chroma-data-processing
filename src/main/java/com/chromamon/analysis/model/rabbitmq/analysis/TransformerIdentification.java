package com.chromamon.analysis.model.rabbitmq.analysis;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TransformerIdentification {
    @JsonProperty("transformer_id")
    private String transformerId;
    @JsonProperty("transformer_name")
    private String transformerName;
    @JsonProperty("location")
    private String location;
    @JsonProperty("installation_date")
    private String installationDate;
}