package com.chromamon.analysis.listener;

import com.chromamon.analysis.mapper.TransformerResultMapper;
import com.chromamon.analysis.model.rabbitmq.ResultData;
import com.chromamon.analysis.model.rabbitmq.analysis.AnalysisData;
import com.chromamon.analysis.service.TransformerAnalysisService;
import com.chromamon.analysis.service.TransformerResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

@Slf4j
@Component
public class RabbitMQListener {

    @Autowired
    private TransformerResultService resultService;

    @Autowired
    private TransformerAnalysisService analysisService;

    private final Queue<ResultData> dataQueue = new LinkedList<>();
    private final Queue<AnalysisData> analysisQueue = new LinkedList<>();

    @RabbitListener(queues = "diagnostic-results")
    public void receiveMessage(@Payload ResultData data) {
        if (data != null) {
            log.info("Diagnostic Received: {}", data);
            dataQueue.offer(data);
            resultService.transformerDataInDatabaseValidation(dataQueue);
            resultService.saveResult(dataQueue.stream().map(TransformerResultMapper::fromRabbitToDTO).collect(Collectors.toList()));
        } else {
            log.error("Received null diagnostic data");
            throw new RuntimeException("Received null diagnostic data");
        }
    }

    @RabbitListener(queues = "processor-queue")
    public void receiveMessage(@Payload AnalysisData data) {
        if (data != null) {
            log.info("Transformer Received: {}", data);
            analysisQueue.offer(data);
            analysisService.addTransformerAnalysis(analysisQueue);
        } else {
            log.error("Received null processed data");
            throw new RuntimeException("Received null processed data");
        }
    }
}
