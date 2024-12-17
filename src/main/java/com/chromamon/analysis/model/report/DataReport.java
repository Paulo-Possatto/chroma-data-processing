package com.chromamon.analysis.model.report;

import com.chromamon.analysis.constants.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Builder
@ToString
public class DataReport {
    private String id;
    private String transformerId;
    private String transformerName;
    private String location;
    private String installationDate;
    private TransformerStatusEnum status;
    private String nominalPower;
    private String primaryVoltage;
    private String secondaryVoltage;
    private TransformerPhaseEnum phase;
    private TransformerConfigurationEnum configuration;
    private Double nominalCurrent;
    private TransformerTypeEnum transformerType;
    private Double transformerImpedance;
    private Integer transformerFrequency;
    private String transformerManufacturer;
    private String fabricationDate;
    private String serialNumber;
    private String lastMaintenanceDate;
    private Integer maxOperatingTemperature;
    private TransformerRefrigerationTypeEnum refrigerationType;
    private TransformerIsolationTypeEnum isolationType;
    private Integer actualLoad;

    private List<AnalysisReport> analysis;
}
