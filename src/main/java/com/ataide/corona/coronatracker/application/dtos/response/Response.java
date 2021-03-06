package com.ataide.corona.coronatracker.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Response {
    @JsonProperty
    private Integer statusCode;
    @JsonProperty
    private LocalDateTime timestamp;
}
