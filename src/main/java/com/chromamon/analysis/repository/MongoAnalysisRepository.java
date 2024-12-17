package com.chromamon.analysis.repository;

import com.chromamon.analysis.model.document.AnalysisDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoAnalysisRepository extends MongoRepository<AnalysisDocument, String> {
}
