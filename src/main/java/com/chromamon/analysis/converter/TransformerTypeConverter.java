package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerTypeConverter implements AttributeConverter<TransformerTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerTypeEnum transformerTypeEnum) {
        if (transformerTypeEnum == null){
            return null;
        }
        switch (transformerTypeEnum){
            case STEP_UP -> {
                return 0;
            }
            case STEP_DOWN -> {
                return 1;
            }
            case AUTO_TRANSFORMER -> {
                return 2;
            }
            case POWER_TRANSFORMER -> {
                return 3;
            }
            case GROUND_TRANSFORMER -> {
                return 4;
            }
            case ISOLATING_TRANSFORMER -> {
                return 5;
            }
            case REGULATION_TRANSFORMER -> {
                return 6;
            }
            case DISTRIBUTION_TRANSFORMER -> {
                return 7;
            }
            case PHASE_ADJUSTING_TRANSFORMER -> {
                return 8;
            }
            default -> throw new IllegalArgumentException(transformerTypeEnum + " not supported");
        }
    }

    @Override
    public TransformerTypeEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 ->{
                return TransformerTypeEnum.STEP_UP;
            }
            case 1 ->{
                return TransformerTypeEnum.STEP_DOWN;
            }
            case 2 ->{
                return TransformerTypeEnum.AUTO_TRANSFORMER;
            }
            case 3 ->{
                return TransformerTypeEnum.POWER_TRANSFORMER;
            }
            case 4 ->{
                return TransformerTypeEnum.GROUND_TRANSFORMER;
            }
            case 5 ->{
                return TransformerTypeEnum.ISOLATING_TRANSFORMER;
            }
            case 6 ->{
                return TransformerTypeEnum.REGULATION_TRANSFORMER;
            }
            case 7 ->{
                return TransformerTypeEnum.DISTRIBUTION_TRANSFORMER;
            }
            case 8 ->{
                return TransformerTypeEnum.PHASE_ADJUSTING_TRANSFORMER;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
