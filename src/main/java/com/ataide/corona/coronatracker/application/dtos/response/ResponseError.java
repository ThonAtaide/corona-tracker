package com.ataide.corona.coronatracker.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseError extends Response {
    @JsonProperty
    private List<String> errors;

    @Builder
    public ResponseError(Integer statusCode, List<String> errors, LocalDateTime timestamp) {
        super(statusCode, timestamp);
        this.errors = errors;
    }
}
