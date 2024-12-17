package com.chromamon.analysis.exception;

import java.util.List;

public class TransformerIdsNotFoundException extends RuntimeException {
    public TransformerIdsNotFoundException(List<String> ids) {
        super((Throwable) ids);
    }
}
