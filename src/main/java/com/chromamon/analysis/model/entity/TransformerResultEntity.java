package com.chromamon.analysis.model.entity;

import com.chromamon.analysis.constants.AnalysisMethodEnum;
import com.chromamon.analysis.converter.AnalysisMethodConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "transformer_results")
public class TransformerResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_identifier", referencedColumnName = "analysis_identifier", nullable = false)
    private TransformerAnalysisEntity transformerAnalysis;

    @Column(name = "analysis_method", nullable = false)
    @Convert(converter = AnalysisMethodConverter.class)
    private AnalysisMethodEnum analysisMethod;

    @Column(name = "analysis_result", nullable = false)
    private String analysisResult;

    @Column(name = "result_identification", nullable = false, unique = true)
    @UuidGenerator
    private UUID resultIdentification;
}

