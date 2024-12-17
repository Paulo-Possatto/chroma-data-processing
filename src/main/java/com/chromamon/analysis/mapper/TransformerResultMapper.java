package com.chromamon.analysis.mapper;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import com.chromamon.analysis.model.dto.TransformerResultDTO;
import com.chromamon.analysis.model.entity.TransformerAnalysisEntity;
import com.chromamon.analysis.model.entity.TransformerResultEntity;
import com.chromamon.analysis.model.rabbitmq.ResultData;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransformerResultMapper {

    public static TransformerResultDTO toDTO(TransformerResultEntity entity){
        if(entity == null){
            return null;
        }
        return TransformerResultDTO.builder()
                .id(entity.getId())
                .analysisMethodEnum(entity.getAnalysisMethod())
                .analysisResult(entity.getAnalysisResult())
                .resultIdentification(entity.getResultIdentification())
                .transformerAnalysisId(entity.getTransformerAnalysis().getTransformerData().getTransformerId())
                .analysisIdentifier(entity.getTransformerAnalysis().getAnalysisIdentifier())
                .build();
    }

    public static TransformerResultEntity toEntity(TransformerResultDTO dto, TransformerAnalysisEntity analysis){
        if(dto==null){
            return null;
        }
        return TransformerResultEntity.builder()
                .id(dto.getId())
                .analysisMethod(dto.getAnalysisMethodEnum())
                .transformerAnalysis(analysis)
                .analysisResult(dto.getAnalysisResult())
                .resultIdentification(dto.getResultIdentification())
                .build();
    }

    public static TransformerResultDTO fromRabbitToDTO(ResultData data){
        if(data == null){
            return null;
        }
        return TransformerResultDTO.builder()
                .analysisResult(data.getResult())
                .transformerAnalysisId(data.getTransformerId())
                .analysisMethodEnum(AnalysisMethodEnum.valueOf(data.getMethod()))
                .analysisIdentifier(data.getAnalysisIdentifier())
                .build();
    }
}
