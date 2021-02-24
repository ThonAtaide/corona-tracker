package com.ataide.corona.coronatracker.application.dtos.response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Integer statusCode;
    private Record data;
    private LocalDateTime timestamp;
}
