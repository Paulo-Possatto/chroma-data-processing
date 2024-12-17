package com.chromamon.analysis.service;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.converter.*;
import com.chromamon.analysis.model.document.AnalysisDocument;
import com.chromamon.analysis.model.report.AnalysisReport;
import com.chromamon.analysis.model.report.DataReport;
import com.chromamon.analysis.model.report.ResultReport;
import com.chromamon.analysis.model.request.RequestReport;
import com.chromamon.analysis.repository.MongoAnalysisRepository;
import com.chromamon.analysis.repository.TransformerDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class SaveReportService {

    @Autowired
    private MongoAnalysisRepository mongoRepository;

    @Autowired
    private TransformerDataRepository dataRepository;

    public void getTransformerDataForReport(RequestReport report) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(PatternConstants.DATE_SEARCH_FORMAT);
            Date startDate = dateFormat.parse(report.getStartDate());
            Date endDate = dateFormat.parse(report.getEndDate());

            List<Object[]> reportData = dataRepository.fetchTransformerReportData(
                    report.getTransformerId(),
                    startDate,
                    endDate
            );
            log.info("reportData={}", reportData);

            DataReport dataReport = processReportData(reportData, report.getTransformerId());
            log.info("DataReport processed: {}", dataReport);

            saveToMongo(dataReport);

        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date for transformer " + report.getTransformerId());
        }
    }

    private DataReport processReportData(List<Object[]> reportData, String transformerId) {
        Map<LocalDateTime, AnalysisReport> analysisMap = new LinkedHashMap<>();

        String transformerName = null;
        String location = null;
        String installationDate = null;
        Integer status = null;
        String nominalPower = null;
        String primaryVoltage = null;
        String secondaryVoltage = null;
        Integer phase = null;
        Integer configuration = null;
        Double nominalCurrent = null;
        Integer transformerType = null;
        Double transformerImpedance = null;
        Integer transformerFrequency = null;
        String transformerManufacturer = null;
        String fabricationDate = null;
        String serialNumber = null;
        String lastMaintenanceDate = null;
        Integer maxOperatingTemperature = null;
        Integer refrigerationType = null;
        Integer isolationType = null;
        Integer actualLoad = null;

        for (Object[] row : reportData) {
            if (transformerName == null) {
                transformerName = (String) row[1];
                actualLoad = (Integer) row[2];
                configuration = (Integer) row[3];
                fabricationDate = (String) row[4];
                installationDate = (String) row[5];
                isolationType = (Integer) row[6];
                lastMaintenanceDate = (String) row[7];
                location = (String) row[8];
                maxOperatingTemperature = (Integer) row[9];
                nominalCurrent = ((Float) row[10]).doubleValue();
                nominalPower = (String) row[11];
                phase = (Integer) row[12];
                primaryVoltage = (String) row[13];
                refrigerationType = (Integer) row[14];
                secondaryVoltage = (String) row[15];
                serialNumber = (String) row[16];
                status = (Integer) row[17];
                transformerFrequency = (Integer) row[18];
                transformerImpedance = ((Float) row[19]).doubleValue();
                transformerManufacturer = (String) row[20];
                transformerType = (Integer) row[21];
            }

            LocalDateTime analysisTimestamp = ((java.sql.Timestamp) row[22]).toLocalDateTime();
            AnalysisReport analysisReport = analysisMap.computeIfAbsent(analysisTimestamp, t -> AnalysisReport.builder()
                    .analysisTimestamp(t)
                    .c2h2Ppm((Integer) row[23])
                    .c2h4Ppm((Integer) row[24])
                    .c2h6Ppm((Integer) row[25])
                    .ch4Ppm((Integer) row[26])
                    .coPpm((Integer) row[27])
                    .co2Ppm((Integer) row[28])
                    .h2Ppm((Integer) row[29])
                    .oilAcidity(((Number) row[30]).doubleValue())
                    .oilPressure(((Number) row[31]).doubleValue())
                    .oilTemperature((Integer) row[32])
                    .results(new ArrayList<>())
                    .build());

            ResultReport resultReport = ResultReport.builder()
                    .analysisMethod(AnalysisMethodConverter.convertAnalysisFromIntToEnum((Integer) row[33]))
                    .analysisResult((String) row[34])
                    .resultIdentification((UUID) row[35])
                    .build();

            analysisReport.getResults().add(resultReport);
        }

        return DataReport.builder()
                .id(UUID.randomUUID().toString())
                .transformerId(transformerId)
                .transformerName(transformerName)
                .location(location)
                .installationDate(installationDate)
                .status(TransformerStatusConverter.convertStatusIntToEnum(status))
                .nominalPower(nominalPower)
                .primaryVoltage(primaryVoltage)
                .secondaryVoltage(secondaryVoltage)
                .phase(TransformerPhaseConverter.convertPhaseIntToEnum(phase))
                .configuration(TransformerConfigurationConverter.convertConfigurationIntToEnum(configuration))
                .nominalCurrent(nominalCurrent)
                .transformerType(TransformerTypeConverter.convertTypeIntToEnum(transformerType))
                .transformerImpedance(transformerImpedance)
                .transformerFrequency(transformerFrequency)
                .transformerManufacturer(transformerManufacturer)
                .fabricationDate(fabricationDate)
                .serialNumber(serialNumber)
                .lastMaintenanceDate(lastMaintenanceDate)
                .maxOperatingTemperature(maxOperatingTemperature)
                .refrigerationType(TransformerRefrigerationConverter.convertRefrigerationIntToEnum(refrigerationType))
                .isolationType(TransformerIsolationTypeConverter.convertIsolationIntToEnum(isolationType))
                .actualLoad(actualLoad)
                .analysis(new ArrayList<>(analysisMap.values()))
                .build();
    }


    private void saveToMongo(DataReport report) {
        AnalysisDocument document = new AnalysisDocument(report);
        log.info("Saving on MongoDB: {}", document);
        mongoRepository.save(document);
        log.info("Document saved!");
    }
}
