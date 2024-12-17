package com.chromamon.analysis.mapper;


import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.model.dto.TransformerAnalysisDTO;
import com.chromamon.analysis.model.entity.TransformerAnalysisEntity;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import com.chromamon.analysis.model.rabbitmq.analysis.AnalysisData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformerAnalysisMapper {

    public static TransformerAnalysisDTO toDTO(TransformerAnalysisEntity entity) {
        return TransformerAnalysisDTO.builder()
                .id(entity.getId())
                .transformerId(entity.getTransformerData().getTransformerId())
                .analysisTimestamp(entity.getAnalysisTimestamp())
                .h2(entity.getH2())
                .co(entity.getCo())
                .co2(entity.getCo2())
                .c2h4(entity.getC2h4())
                .c2h6(entity.getC2h6())
                .ch4(entity.getCh4())
                .c2h2(entity.getC2h2())
                .oilAcidity(entity.getOilAcidity())
                .oilTemperature(entity.getOilTemperature())
                .oilPressure(entity.getOilPressure())
                .analysisIdentifier(entity.getAnalysisIdentifier())
                .build();
    }

    public static TransformerAnalysisEntity toEntity(TransformerAnalysisDTO dto, TransformerDataEntity transformerData) {
        return TransformerAnalysisEntity.builder()
                .id(dto.getId())
                .transformerData(transformerData)
                .analysisTimestamp(dto.getAnalysisTimestamp())
                .h2(dto.getH2())
                .co(dto.getCo())
                .co2(dto.getCo2())
                .c2h4(dto.getC2h4())
                .c2h6(dto.getC2h6())
                .ch4(dto.getCh4())
                .c2h2(dto.getC2h2())
                .oilAcidity(dto.getOilAcidity())
                .oilTemperature(dto.getOilTemperature())
                .oilPressure(dto.getOilPressure())
                .analysisIdentifier(dto.getAnalysisIdentifier())
                .build();
    }

    public static TransformerAnalysisDTO fromRabbitMQToDTO(AnalysisData data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(PatternConstants.ISO_DATE_FORMAT);
        Date analysisTimestamp = dateFormat.parse(data.getChromatographyData().getAnalysisTimestamp());
        return TransformerAnalysisDTO.builder()
                .transformerId(data.getTransformerIdentification().getTransformerId())
                .ch4(data.getChromatographyData().getCh4())
                .co(data.getChromatographyData().getCo())
                .co2(data.getChromatographyData().getCo2())
                .h2(data.getChromatographyData().getH2())
                .c2h6(data.getChromatographyData().getC2h6())
                .c2h2(data.getChromatographyData().getC2h2())
                .c2h4(data.getChromatographyData().getC2h4())
                .analysisTimestamp(analysisTimestamp)
                .oilPressure(data.getChromatographyData().getOilPressure())
                .oilTemperature(data.getChromatographyData().getOilTemperature())
                .oilAcidity(data.getChromatographyData().getOilAcidity())
                .analysisIdentifier(data.getAnalysisIdentifier())
                .build();
    }
}
