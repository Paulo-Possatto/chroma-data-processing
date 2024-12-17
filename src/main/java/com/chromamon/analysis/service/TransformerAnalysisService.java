package com.chromamon.analysis.service;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.exception.TransformerNotFoundException;
import com.chromamon.analysis.mapper.TransformerAnalysisMapper;
import com.chromamon.analysis.model.dto.TransformerAnalysisDTO;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import com.chromamon.analysis.model.rabbitmq.analysis.AnalysisData;
import com.chromamon.analysis.repository.TransformerAnalysisRepository;
import com.chromamon.analysis.repository.TransformerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Queue;

@Slf4j
@Service
public class TransformerAnalysisService {

    @Autowired
    private TransformerAnalysisRepository analysisRepository;

    @Autowired
    private TransformerDataRepository dataRepository;

    public void addTransformerAnalysis(Queue<AnalysisData> dataQueue){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(PatternConstants.ISO_DATE_FORMAT);
            for (AnalysisData data : dataQueue) {
                if(!analysisRepository.isTransformerAnalysisInDatabase(
                        data.getTransformerIdentification().getTransformerId(),
                        dateFormat.parse(data.getChromatographyData().getAnalysisTimestamp()))
                ){
                    TransformerDataEntity entity = dataRepository.findTransformerDataByIdentification(data.getTransformerIdentification().getTransformerId()).orElseThrow(
                            () -> new TransformerNotFoundException("No transformer data with ID " + data.getTransformerIdentification().getTransformerId())
                    );
                    TransformerAnalysisDTO dto = TransformerAnalysisMapper.fromRabbitMQToDTO(data);
                    analysisRepository.save(TransformerAnalysisMapper.toEntity(dto, entity));
                }
                else {
                    log.info("Transformer analysis already saved! {} - {}",
                            data.getTransformerIdentification().getTransformerId(),
                            data.getChromatographyData().getAnalysisTimestamp()
                    );
                }
            }
        } catch (ParseException e){
            throw new RuntimeException(e);
        }
    }
}
