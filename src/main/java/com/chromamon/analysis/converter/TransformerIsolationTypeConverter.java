package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerIsolationType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerIsolationTypeConverter implements AttributeConverter<TransformerIsolationType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerIsolationType transformerIsolationType) {
        if(transformerIsolationType == null){
            return null;
        }
        switch (transformerIsolationType){
            case SOLID -> {
                return 0;
            }
            case MINERAL_OIL -> {
                return 1;
            }
            case VEGETABLE_OIL -> {
                return 2;
            }
            default -> throw new IllegalArgumentException(transformerIsolationType + " not supported");
        }
    }

    @Override
    public TransformerIsolationType convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerIsolationType.SOLID;
            }
            case 1 -> {
                return TransformerIsolationType.MINERAL_OIL;
            }
            case 2 -> {
                return TransformerIsolationType.VEGETABLE_OIL;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
