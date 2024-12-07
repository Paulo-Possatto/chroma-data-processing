package com.chromamon.analysis.repository;

import com.chromamon.analysis.model.entity.TransformerAnalysisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerAnalysisRepository extends JpaRepository<TransformerAnalysisEntity, Long> {
}
