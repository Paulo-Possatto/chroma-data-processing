package com.chromamon.analysis.mapper;

import com.chromamon.analysis.model.dto.TransformerDataDTO;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransformerDataMapper {

    public static TransformerDataEntity toEntity(TransformerDataDTO dto) {
        if (dto == null) {
            return null;
        }

        return TransformerDataEntity.builder()
                .id(dto.getId())
                .transformerId(dto.getTransformerId())
                .transformerName(dto.getTransformerName())
                .location(dto.getLocation())
                .installationDate(dto.getInstallationDate())
                .status(dto.getStatus())
                .nominalPower(dto.getNominalPower())
                .primaryVoltage(dto.getPrimaryVoltage())
                .secondaryVoltage(dto.getSecondaryVoltage())
                .phase(dto.getPhase())
                .configuration(dto.getConfiguration())
                .nominalCurrent(dto.getNominalCurrent())
                .transformerType(dto.getTransformerType())
                .transformerImpedance(dto.getTransformerImpedance())
                .transformerFrequency(dto.getTransformerFrequency())
                .transformerManufacturer(dto.getTransformerManufacturer())
                .fabricationDate(dto.getFabricationDate())
                .serialNumber(dto.getSerialNumber())
                .lastMaintenanceDate(dto.getLastMaintenanceDate())
                .maxOperatingTemperature(dto.getMaxOperatingTemperature())
                .refrigerationType(dto.getRefrigerationType())
                .isolationType(dto.getIsolationType())
                .actualLoad(dto.getActualLoad())
                .build();
    }

    public static TransformerDataDTO toDTO(TransformerDataEntity entity) {
        if (entity == null) {
            return null;
        }

        return TransformerDataDTO.builder()
                .id(entity.getId())
                .transformerId(entity.getTransformerId())
                .transformerName(entity.getTransformerName())
                .location(entity.getLocation())
                .installationDate(entity.getInstallationDate())
                .status(entity.getStatus())
                .nominalPower(entity.getNominalPower())
                .primaryVoltage(entity.getPrimaryVoltage())
                .secondaryVoltage(entity.getSecondaryVoltage())
                .phase(entity.getPhase())
                .configuration(entity.getConfiguration())
                .nominalCurrent(entity.getNominalCurrent())
                .transformerType(entity.getTransformerType())
                .transformerImpedance(entity.getTransformerImpedance())
                .transformerFrequency(entity.getTransformerFrequency())
                .transformerManufacturer(entity.getTransformerManufacturer())
                .fabricationDate(entity.getFabricationDate())
                .serialNumber(entity.getSerialNumber())
                .lastMaintenanceDate(entity.getLastMaintenanceDate())
                .maxOperatingTemperature(entity.getMaxOperatingTemperature())
                .refrigerationType(entity.getRefrigerationType())
                .isolationType(entity.getIsolationType())
                .actualLoad(entity.getActualLoad())
                .build();
    }
}
