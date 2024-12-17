package com.chromamon.analysis.repository;

import com.chromamon.analysis.model.entity.TransformerAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface TransformerAnalysisRepository extends JpaRepository<TransformerAnalysisEntity, Long> {

    @Query("SELECT t FROM TransformerAnalysisEntity t WHERE t.transformerData.transformerId = :transformerId")
    Optional<TransformerAnalysisEntity> findByTransformerId(
            @Param("transformerId") String transformerId
    );

    @Query("SELECT COUNT(t)>0 FROM TransformerAnalysisEntity t WHERE t.transformerData.transformerId = :transformerId AND t.analysisTimestamp = :analysisTimestamp")
    boolean isTransformerAnalysisInDatabase(
            @Param("transformerId") String transformerId,
            @Param("analysisTimestamp") Date analysisTimestamp
    );

    @Query("SELECT t FROM TransformerAnalysisEntity t WHERE t.analysisIdentifier = :analysisIdentifier")
    Optional<TransformerAnalysisEntity> findByAnalysisIdentifier(
            @Param("analysisIdentifier") String analysisIdentifier
    );

}
