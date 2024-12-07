package com.chromamon.analysis.model.request;

import com.chromamon.analysis.constants.TransformerStatusEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestTransformersByStatus {

    @NotNull(message = "The transformer status is required")
    private TransformerStatusEnum status;
}
