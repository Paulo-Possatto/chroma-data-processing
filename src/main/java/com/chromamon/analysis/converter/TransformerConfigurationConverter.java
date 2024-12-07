package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerConfigurationEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerConfigurationConverter implements AttributeConverter<TransformerConfigurationEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerConfigurationEnum transformerConfigurationEnum) {
        if(transformerConfigurationEnum == null){
            return null;
        }
        switch (transformerConfigurationEnum){
            case STAR_STAR -> {
                return 0;
            }
            case DELTA_STAR -> {
                return 1;
            }
            case STAR_DELTA -> {
                return 2;
            }
            case DELTA_DELTA -> {
                return 3;
            }
            default -> throw new IllegalArgumentException(transformerConfigurationEnum + " not supported");
        }
    }

    @Override
    public TransformerConfigurationEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerConfigurationEnum.STAR_STAR;
            }
            case 1 -> {
                return TransformerConfigurationEnum.DELTA_STAR;
            }
            case 2 -> {
                return TransformerConfigurationEnum.STAR_DELTA;
            }
            case 3 -> {
                return TransformerConfigurationEnum.DELTA_DELTA;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
