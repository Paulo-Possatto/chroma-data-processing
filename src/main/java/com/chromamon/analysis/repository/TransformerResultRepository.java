package com.chromamon.analysis.repository;

import com.chromamon.analysis.model.entity.TransformerResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerResultRepository extends JpaRepository<TransformerResultEntity, Long> {
}
