package com.chromamon.analysis.model.document;

import com.chromamon.analysis.model.report.DataReport;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "analysis_report")
@AllArgsConstructor
@Builder
@Data
public class AnalysisDocument {

    @Id
    private String id;
    private DataReport report;
    private Date requestDate;

    public AnalysisDocument(DataReport data) {
        this.report = data;
        this.requestDate = new Date();
    }
}
