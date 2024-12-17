package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.TransformerPhaseEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TransformerPhaseConverter implements AttributeConverter<TransformerPhaseEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(TransformerPhaseEnum transformerPhaseEnum) {
        if(transformerPhaseEnum == null){
            return null;
        }
        switch (transformerPhaseEnum){
            case SINGLE_PHASE -> {
                return 0;
            }
            case THREE_PHASE -> {
                return 1;
            }
            case MULTIPHASE -> {
                return 2;
            }
            default -> throw new IllegalArgumentException(transformerPhaseEnum + " not supported");
        }
    }

    @Override
    public TransformerPhaseEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerPhaseEnum.SINGLE_PHASE;
            }
            case 1 -> {
                return TransformerPhaseEnum.THREE_PHASE;
            }
            case 2 -> {
                return TransformerPhaseEnum.MULTIPHASE;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }

    public static TransformerPhaseEnum convertPhaseIntToEnum(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return TransformerPhaseEnum.SINGLE_PHASE;
            }
            case 1 -> {
                return TransformerPhaseEnum.THREE_PHASE;
            }
            case 2 -> {
                return TransformerPhaseEnum.MULTIPHASE;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
