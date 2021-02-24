package com.ataide.corona.coronatracker.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorRecord implements Record {
    private List<String> errors;
}
