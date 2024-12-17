package com.chromamon.analysis.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Builder
@Table(name = "transformer_analysis")
public class TransformerAnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transformer_id", referencedColumnName = "transformer_id", nullable = false)
    private TransformerDataEntity transformerData;

    @OneToMany(mappedBy = "transformerAnalysis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransformerResultEntity> transformerResultList = new ArrayList<>();

    @Column(name = "analysis_timestamp", nullable = false)
    private Date analysisTimestamp;

    @Column(name = "h2_ppm", nullable = false)
    private Integer h2;

    @Column(name = "co_ppm", nullable = false)
    private Integer co;

    @Column(name = "co2_ppm", nullable = false)
    private Integer co2;

    @Column(name = "c2h4_ppm", nullable = false)
    private Integer c2h4;

    @Column(name = "c2h6_ppm", nullable = false)
    private Integer c2h6;

    @Column(name = "ch4_ppm", nullable = false)
    private Integer ch4;

    @Column(name = "c2h2_ppm", nullable = false)
    private Integer c2h2;

    @Column(name = "oil_acidity", nullable = false)
    private Double oilAcidity;

    @Column(name = "oil_temperature", nullable = false)
    private Integer oilTemperature;

    @Column(name = "oil_pressure", nullable = false)
    private Double oilPressure;

    @Column(name = "analysis_identifier", nullable = false, unique = true)
    private String analysisIdentifier;
}

