package com.chromamon.analysis.model.entity;

import com.chromamon.analysis.constants.*;
import com.chromamon.analysis.converter.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "transformer_data")
public class TransformerDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transformer_id", nullable = false)
    private String transformerId;

    @Column(name = "transformer_name", nullable = false)
    private String transformerName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "installation_date", nullable = false)
    private String installationDate;

    @Column(name = "status", nullable = false)
    @Convert(converter = TransformerStatusConverter.class)
    private TransformerStatusEnum status;

    @Column(name = "nominal_power", nullable = false)
    private String nominalPower;

    @Column(name = "primary_voltage", nullable = false)
    private String primaryVoltage;

    @Column(name = "secondary_voltage", nullable = false)
    private String secondaryVoltage;

    @Column(name = "phase", nullable = false)
    @Convert(converter = TransformerPhaseConverter.class)
    private TransformerPhaseEnum phase;

    @Column(name = "configuration")
    @Convert(converter = TransformerConfigurationConverter.class)
    private TransformerConfigurationEnum configuration;

    @Column(name = "nominal_current", nullable = false, precision = 2)
    private Double nominalCurrent;

    @Column(name = "transformer_type", nullable = false)
    @Convert(converter = TransformerTypeConverter.class)
    private TransformerTypeEnum transformerType;

    @Column(name = "transformer_impedance", nullable = false, precision = 2)
    private Double transformerImpedance;

    @Column(name = "transformer_frequency", nullable = false)
    private Integer transformerFrequency;

    @Column(name = "transformer_manufacturer", nullable = false)
    private String transformerManufacturer;

    @Column(name = "fabrication_date", nullable = false)
    private String fabricationDate;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "last_maintenance_date", nullable = false)
    private String lastMaintenanceDate;

    @Column(name = "max_operating_temp", nullable = false)
    private Integer maxOperatingTemperature;

    @Column(name = "refrigeration_type", nullable = false)
    @Convert(converter = TransformerRefrigerationConverter.class)
    private TransformerRefrigerationTypeEnum refrigerationType;

    @Column(name = "isolation_type", nullable = false)
    @Convert(converter = TransformerIsolationTypeConverter.class)
    private TransformerIsolationType isolationType;

    @Column(name = "actual_load", nullable = false)
    private Integer actualLoad;
}
