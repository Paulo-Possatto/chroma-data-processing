package com.chromamon.analysis.model.dto;

import com.chromamon.analysis.constants.*;
import com.chromamon.analysis.validator.DateFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransformerDataDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotBlank(message = "The transformer identification cannot be empty")
    @NotNull(message = "The transformer identification is required")
    @Size(max = 40, message = "The transformer identification must not exceed 40 characters")
    private String transformerId;

    @NotBlank(message = "The transformer name cannot be empty")
    @NotNull(message = "The transformer name is required")
    @Size(max = 40, message = "The transformer name must not exceed 40 characters")
    private String transformerName;

    @NotBlank(message = "The transformer location cannot be empty")
    @NotNull(message = "The transformer location is required")
    @Size(max = 40, message = "The transformer location must not exceed 40 characters")
    private String location;

    @DateFormat(pattern = PatternConstants.DATE_FORMAT, message = "The installation date format must be " + PatternConstants.DATE_FORMAT)
    @NotBlank(message = "The installation date cannot be empty")
    @NotNull(message = "The installation date is required")
    private String installationDate;

    @NotNull(message = "The transformer status is required")
    private TransformerStatusEnum status;

    @NotBlank(message = "The nominal power cannot be empty")
    @NotNull(message = "The nominal power is required")
    @Size(max = 10, message = "The nominal power must not exceed 10 characters")
    private String nominalPower;

    @NotBlank(message = "The primary voltage cannot be empty")
    @NotNull(message = "The primary voltage is required")
    @Size(max = 10, message = "The primary voltage must not exceed 10 characters")
    private String primaryVoltage;

    @NotBlank(message = "The secondary voltage cannot be empty")
    @NotNull(message = "The secondary voltage is required")
    @Size(max = 10, message = "The secondary voltage must not exceed 10 characters")
    private String secondaryVoltage;

    @NotNull(message = "The transformer phase is required")
    private TransformerPhaseEnum phase;

    private TransformerConfigurationEnum configuration;

    @Positive(message = "The nominal current must be a positive value")
    @NotNull(message = "The nominal current is required")
    private Double nominalCurrent;

    @NotNull(message = "The transformer type is required")
    private TransformerTypeEnum transformerType;

    @Positive(message = "The transformer impedance must be a positive value")
    @NotNull(message = "The transformer impedance is required")
    private Double transformerImpedance;

    @Positive(message = "The transformer frequency must be a positive value")
    @NotNull(message = "The transformer frequency is required")
    private Integer transformerFrequency;

    @NotBlank(message = "The transformer manufacturer cannot be empty")
    @NotNull(message = "The transformer manufacturer is required")
    @Size(max = 40, message = "The transformer manufacturer must not exceed 40 characters")
    private String transformerManufacturer;

    @DateFormat(pattern = PatternConstants.DATE_FORMAT, message = "The fabrication date format must be " + PatternConstants.DATE_FORMAT)
    @NotBlank(message = "The fabrication date cannot be empty")
    @NotNull(message = "The fabrication date is required")
    private String fabricationDate;

    @NotBlank(message = "The serial number cannot be empty")
    @NotNull(message = "The serial number is required")
    @Size(max = 40, message = "The serial number must not exceed 40 characters")
    private String serialNumber;

    @DateFormat(pattern = PatternConstants.DATE_FORMAT, message = "The last maintenance date format must be "+ PatternConstants.DATE_FORMAT)
    @NotBlank(message = "The last maintenance date cannot be empty")
    @NotNull(message = "The last maintenance date is required")
    private String lastMaintenanceDate;

    @Positive(message = "The maximum operating temperature must be a positive value")
    @NotNull(message = "The maximum operating temperature is required")
    private Integer maxOperatingTemperature;

    @NotNull(message = "The transformer refrigeration type is required")
    private TransformerRefrigerationTypeEnum refrigerationType;

    @NotNull(message = "The transformer isolation type is required")
    private TransformerIsolationTypeEnum isolationType;

    @Positive(message = "The actual load must be a positive value")
    @NotNull(message = "The actual load is required")
    private Integer actualLoad;
}