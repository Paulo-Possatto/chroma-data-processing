package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerRefrigerationTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerRefrigerationConverter implements AttributeConverter<TransformerRefrigerationTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerRefrigerationTypeEnum transformerRefrigerationTypeEnum) {
        if(transformerRefrigerationTypeEnum == null){
            return null;
        }
        switch (transformerRefrigerationTypeEnum){
            case AIR_FORCE -> {
                return 0;
            }
            case AIR_NATURAL -> {
                return 1;
            }
            case OIL_FORCED_AIR_FORCED -> {
                return 2;
            }
            case OIL_FORCED_AIR_NATURAL -> {
                return 3;
            }
            case OIL_NATURAL_AIR_FORCED -> {
                return 4;
            }
            case OIL_NATURAL_AIR_NATURAL -> {
                return 5;
            }
            default -> throw new IllegalArgumentException(transformerRefrigerationTypeEnum + " not supported");
        }
    }

    @Override
    public TransformerRefrigerationTypeEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerRefrigerationTypeEnum.AIR_FORCE;
            }
            case 1 -> {
                return TransformerRefrigerationTypeEnum.AIR_NATURAL;
            }
            case 2 -> {
                return TransformerRefrigerationTypeEnum.OIL_FORCED_AIR_FORCED;
            }
            case 3 -> {
                return TransformerRefrigerationTypeEnum.OIL_FORCED_AIR_NATURAL;
            }
            case 4 -> {
                return TransformerRefrigerationTypeEnum.OIL_NATURAL_AIR_FORCED;
            }
            case 5 -> {
                return TransformerRefrigerationTypeEnum.OIL_NATURAL_AIR_NATURAL;
            }
            default -> throw new IllegalArgumentException(integer + "not supported");
        }
    }
}
