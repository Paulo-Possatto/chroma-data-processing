package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerIsolationTypeEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerIsolationTypeConverter implements AttributeConverter<TransformerIsolationTypeEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerIsolationTypeEnum transformerIsolationTypeEnum) {
        if(transformerIsolationTypeEnum == null){
            return null;
        }
        switch (transformerIsolationTypeEnum){
            case SOLID -> {
                return 0;
            }
            case MINERAL_OIL -> {
                return 1;
            }
            case VEGETABLE_OIL -> {
                return 2;
            }
            default -> throw new IllegalArgumentException(transformerIsolationTypeEnum + " not supported");
        }
    }

    @Override
    public TransformerIsolationTypeEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerIsolationTypeEnum.SOLID;
            }
            case 1 -> {
                return TransformerIsolationTypeEnum.MINERAL_OIL;
            }
            case 2 -> {
                return TransformerIsolationTypeEnum.VEGETABLE_OIL;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }

    public static TransformerIsolationTypeEnum convertIsolationIntToEnum(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerIsolationTypeEnum.SOLID;
            }
            case 1 -> {
                return TransformerIsolationTypeEnum.MINERAL_OIL;
            }
            case 2 -> {
                return TransformerIsolationTypeEnum.VEGETABLE_OIL;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
