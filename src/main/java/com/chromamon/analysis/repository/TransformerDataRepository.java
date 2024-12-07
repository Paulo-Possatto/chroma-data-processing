package com.chromamon.analysis.repository;

import com.chromamon.analysis.constants.TransformerStatusEnum;
import com.chromamon.analysis.model.entity.TransformerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}
