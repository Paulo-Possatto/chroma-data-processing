package com.chromamon.analysis.converter;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AnalysisMethodConverter implements AttributeConverter<AnalysisMethodEnum, Integer> {
    @Override
    public Integer convertToDatabaseColumn(AnalysisMethodEnum analysisMethodEnum) {
        if(analysisMethodEnum == null){
            return null;
        }
        switch (analysisMethodEnum){
            case ROGERS -> {
                return 0;
            }
            case IEEE -> {
                return 1;
            }
            case DUVAL -> {
                return 2;
            }
            case KGA -> {
                return 3;
            }
            default -> throw new IllegalArgumentException(analysisMethodEnum + " not supported");
        }
    }

    @Override
    public AnalysisMethodEnum convertToEntityAttribute(Integer integer) {
        if(integer == null){
            return null;
        }
        switch (integer){
            case 0 -> {
                return AnalysisMethodEnum.ROGERS;
            }
            case 1 -> {
                return AnalysisMethodEnum.IEEE;
            }
            case 2 -> {
                return AnalysisMethodEnum.DUVAL;
            }
            case 3 -> {
                return AnalysisMethodEnum.KGA;
            }
            default -> throw new IllegalArgumentException(integer + " not supported");
        }
    }
}
