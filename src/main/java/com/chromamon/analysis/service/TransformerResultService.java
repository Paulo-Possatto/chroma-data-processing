package com.chromamon.analysis.service;

import com.chromamon.analysis.exception.TransformerIdsNotFoundException;
import com.chromamon.analysis.mapper.TransformerResultMapper;
import com.chromamon.analysis.model.dto.TransformerResultDTO;
import com.chromamon.analysis.model.entity.TransformerAnalysisEntity;
import com.chromamon.analysis.model.entity.TransformerResultEntity;
import com.chromamon.analysis.model.rabbitmq.ResultData;
import com.chromamon.analysis.repository.TransformerAnalysisRepository;
import com.chromamon.analysis.repository.TransformerDataRepository;
import com.chromamon.analysis.repository.TransformerResultRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Slf4j
@Service
public class TransformerResultService {

    @Autowired
    private TransformerResultRepository resultRepository;

    @Autowired
    private TransformerDataRepository dataRepository;

    @Autowired
    private TransformerAnalysisRepository analysisRepository;

    public void transformerDataInDatabaseValidation(Queue<ResultData> dataQueue){
        List<String> notFoundTransformerIds = new ArrayList<>();
        for(ResultData data : dataQueue){
            if(!dataRepository.isTransformerInDatabase(data.getTransformerId())){
                notFoundTransformerIds.add(data.getTransformerId());
            }
        }
        if(!notFoundTransformerIds.isEmpty()){
            throw new TransformerIdsNotFoundException(
                    notFoundTransformerIds
            );
        }
    }

    public void saveResult(List<TransformerResultDTO> dtos){

        for(TransformerResultDTO dto : dtos){
            if(!resultRepository.isResultPresent(
                    dto.getAnalysisMethodEnum(),
                    dto.getAnalysisIdentifier()
            )){
                TransformerAnalysisEntity analysisEntity = analysisRepository.findByAnalysisIdentifier(dto.getAnalysisIdentifier())
                        .orElseThrow(() -> new RuntimeException("Analysis not found"));

                TransformerResultEntity entity = TransformerResultMapper.toEntity(dto, analysisEntity);

                resultRepository.save(entity);
            } else {
                log.info("Transformer result already saved! {} - {}",
                        dto.getAnalysisMethodEnum(),
                        dto.getAnalysisIdentifier());
            }

        }
    }
}
