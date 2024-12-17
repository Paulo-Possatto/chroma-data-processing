package com.chromamon.analysis.repository;

import com.chromamon.analysis.constants.TransformerStatusEnum;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransformerDataRepository extends JpaRepository<TransformerDataEntity, Long> {

    @Query("SELECT COUNT(t)>0 FROM TransformerDataEntity t WHERE t.transformerId = :transformerId")
    boolean isTransformerInDatabase(
            @Param("transformerId") String transformerId
    );

    @Query("SELECT t FROM TransformerDataEntity t WHERE t.transformerId = :transformerId")
    Optional<TransformerDataEntity> findTransformerDataByIdentification(
            @Param("transformerId") String transformerId
    );

    @Query("SELECT t FROM TransformerDataEntity t WHERE t.status = :status ORDER BY t.installationDate ASC")
    List<TransformerDataEntity> findTransformerByStatus(
            @Param("status")TransformerStatusEnum status
    );

    @Query(value = """
        SELECT 
            td.transformer_id AS transformerId,
            td.transformer_name AS transformerName,
            td.actual_load AS actualLoad,
            td.configuration AS configuration,
            td.fabrication_date AS fabricationDate,
            td.installation_date as installationDate,
            td.isolation_type AS isolationType,
            td.last_maintenance_date AS lastMaintenanceDate,
            td.location AS location,
            td.max_operating_temp AS maxOperatingTemperature,
            td.nominal_current AS nominalCurrent,
            td.nominal_power AS nominalPower,
            td.phase AS phase,
            td.primary_voltage AS primaryVoltage,
            td.refrigeration_type AS refrigerationType,
            td.secondary_voltage AS secondaryVoltage,
            td.serial_number AS serialNumber,
            td.status AS status,
            td.transformer_frequency AS transformerFrequency,
            td.transformer_impedance AS transformerImpedance,
            td.transformer_manufacturer AS transformerManufacturer,
            td.transformer_type AS transformerType,
            ta.analysis_timestamp AS analysisTimestamp,
            ta.c2h2_ppm AS c2h2Ppm,
            ta.c2h4_ppm AS c2h4Ppm,
            ta.c2h6_ppm AS c2h6Ppm,
            ta.ch4_ppm AS ch4Ppm,
            ta.co_ppm AS coPpm,
            ta.co2_ppm AS co2Ppm,
            ta.h2_ppm AS h2Ppm,
            ta.oil_acidity AS oilAcidity,
            ta.oil_pressure AS oilPressure,
            ta.oil_temperature AS oilTemperature,
            tr.analysis_method AS analysisMethod,
            tr.analysis_result AS analysisResult,
            tr.result_identification AS resultIdentification
        FROM 
            transformer_data td
        JOIN 
            transformer_analysis ta 
            ON td.transformer_id = ta.transformer_id
        JOIN 
            transformer_results tr 
            ON ta.analysis_identifier = tr.analysis_identifier
        WHERE 
            td.transformer_id = :transformerId
            AND ta.analysis_timestamp BETWEEN :startDate AND :endDate
        ORDER BY 
            ta.analysis_timestamp, tr.result_identification
        """, nativeQuery = true)
    List<Object[]> fetchTransformerReportData(
            @Param("transformerId") String transformerId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}
