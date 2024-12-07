package com.chromamon.analysis.model.request;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.constants.TransformerStatusEnum;
import com.chromamon.analysis.validator.DateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestUpdateTransformerStatus {
    @NotNull(message = "The transformer status is required")
    private TransformerStatusEnum status;

    @DateFormat(pattern = PatternConstants.DATE_FORMAT, message = "The last maintenance date format must be "+ PatternConstants.DATE_FORMAT)
    @NotBlank(message = "The last maintenance date cannot be empty")
    @NotNull(message = "The last maintenance date is required")
    private String lastMaintenanceDate;
}
