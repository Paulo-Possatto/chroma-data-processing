package com.chromamon.analysis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = com.chromamon.analysis.repository.MongoAnalysisRepository.class)
public class MongoConfig {
}
