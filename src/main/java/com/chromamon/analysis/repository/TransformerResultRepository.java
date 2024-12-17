package com.chromamon.analysis.repository;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import com.chromamon.analysis.model.entity.TransformerResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerResultRepository extends JpaRepository<TransformerResultEntity, Long> {

    @Query("SELECT COUNT(t)>0 FROM TransformerResultEntity t WHERE t.analysisMethod = :analysisMethod AND t.transformerAnalysis.analysisIdentifier = :analysisIdentifier")
    boolean isResultPresent(
            @Param("analysisMethod")AnalysisMethodEnum analysisMethod,
            @Param("analysisIdentifier") String analysisIdentifier
            );
}
