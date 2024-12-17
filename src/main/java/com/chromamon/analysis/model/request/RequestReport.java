package com.chromamon.analysis.model.request;

import com.chromamon.analysis.constants.PatternConstants;
import com.chromamon.analysis.validator.DateFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestReport {

    @NotBlank(message = "The transformer identification cannot be empty")
    @NotNull(message = "The transformer identification is required")
    @Size(max = 40, message = "The transformer identification must not exceed 40 characters")
    private String transformerId;

    @NotBlank(message = "Start Date cannot be empty")
    @NotNull(message = "Start Date is required.")
    @DateFormat(pattern = PatternConstants.DATE_SEARCH_FORMAT, message = "The Start Date must be in the " + PatternConstants.DATE_SEARCH_FORMAT + " format")
    private String startDate;

    @NotBlank(message = "End Date cannot be empty")
    @NotNull(message = "End Date is required.")
    @DateFormat(pattern = PatternConstants.DATE_SEARCH_FORMAT, message = "The End Date must be in the " + PatternConstants.DATE_SEARCH_FORMAT + " format")
    private String endDate;
}
