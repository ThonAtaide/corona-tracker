package com.ataide.corona.coronatracker.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData extends Response {
    @JsonProperty
    private RecordData data;

    @Builder
    public ResponseData(Integer statusCode, RecordData data, LocalDateTime timestamp) {
        super(statusCode, timestamp);
        this.data = data;
    }
}
