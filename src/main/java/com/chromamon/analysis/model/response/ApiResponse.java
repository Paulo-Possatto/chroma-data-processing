package com.chromamon.analysis.model.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private String message;
    private T data;
    private LocalDateTime time;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
        this.time = LocalDateTime.now();
    }
}
