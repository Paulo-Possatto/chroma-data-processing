package com.chromamon.analysis.model.dto;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.validator.DateFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransformerAnalysisDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "The transformer identification cannot be empty")
    @NotNull(message = "The transformer identification is required")
    @Size(max = 40, message = "The transformer identification must not exceed 40 characters")
    private String transformerId;

    @NotBlank(message = "Analysis timestamp cannot be empty")
    @NotNull(message = "Analysis timestamp is required.")
    @DateFormat(pattern = PatternConstants.ISO_DATE_FORMAT, message = "The analysis timestamp must be in the " + PatternConstants.ISO_DATE_FORMAT + " format")
    private Date analysisTimestamp;

    @NotBlank(message = "H2 value cannot be empty")
    @NotNull(message = "H2 value is required.")
    @Min(value = 0, message = "H2 value must be non-negative.")
    private Integer h2;

    @NotBlank(message = "CO value cannot be empty")
    @NotNull(message = "CO value is required.")
    @Min(value = 0, message = "CO value must be non-negative.")
    private Integer co;

    @NotBlank(message = "CO2 value cannot be empty")
    @NotNull(message = "CO2 value is required.")
    @Min(value = 0, message = "CO2 value must be non-negative.")
    private Integer co2;

    @NotBlank(message = "C2H4 value cannot be empty")
    @NotNull(message = "C2H4 value is required.")
    @Min(value = 0, message = "C2H4 value must be non-negative.")
    private Integer c2h4;

    @NotBlank(message = "C2H6 value cannot be empty")
    @NotNull(message = "C2H6 value is required.")
    @Min(value = 0, message = "C2H6 value must be non-negative.")
    private Integer c2h6;

    @NotBlank(message = "CH4 value cannot be empty")
    @NotNull(message = "CH4 value is required.")
    @Min(value = 0, message = "CH4 value must be non-negative.")
    private Integer ch4;

    @NotBlank(message = "C2H2 value cannot be empty")
    @NotNull(message = "C2H2 value is required.")
    @Min(value = 0, message = "C2H2 value must be non-negative.")
    private Integer c2h2;

    @NotNull(message = "Oil acidity is required.")
    @DecimalMin(value = "0.0", message = "Oil acidity must be non-negative.")
    private Double oilAcidity;

    @NotNull(message = "Oil temperature is required.")
    private Integer oilTemperature;

    @NotNull(message = "Oil pressure is required.")
    @DecimalMin(value = "0.0", message = "Oil pressure must be non-negative.")
    private Double oilPressure;

    @NotNull(message = "The analysis identifier is required")
    @NotBlank(message = "Analysis identifier cannot be empty")
    private String analysisIdentifier;
}
