package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerStatusEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerStatusConverter implements AttributeConverter<TransformerStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TransformerStatusEnum transformerStatusEnum) {
        if(transformerStatusEnum == null){
            return null;
        }
        switch (transformerStatusEnum){
            case OPERATING -> {
                return 2;
            }
            case IN_MAINTENANCE -> {
                return 1;
            }
            case NOT_OPERATING -> {
                return 0;
            }
            default -> throw new IllegalArgumentException(transformerStatusEnum + " not supported");
        }
    }

    @Override
    public TransformerStatusEnum convertToEntityAttribute(Integer value) {
        if(value == null) {
            return null;
        } switch (value){
            case 0 -> {
                return TransformerStatusEnum.NOT_OPERATING;
            }
            case 1 -> {
                return TransformerStatusEnum.IN_MAINTENANCE;
            }
            case 2 -> {
                return TransformerStatusEnum.OPERATING;
            }
            default -> throw new IllegalArgumentException(value + " not supported");
        }
    }
}
