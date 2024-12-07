package com.chromamon.analysis.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = "transformer_analysis")
@Builder
public class TransformerAnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transformer_id", nullable = false)
    private TransformerDataEntity transformerData;

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
}
