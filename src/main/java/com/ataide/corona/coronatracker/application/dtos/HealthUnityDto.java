package com.ataide.corona.coronatracker.application.dtos;

import com.ataide.corona.coronatracker.application.dtos.response.RecordData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.Size;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class HealthUnityDto implements ProfileType, RecordData {

    @JsonProperty
    private Long id;

    @JsonProperty
    @Size(min = 3, max = 50, message="O nome deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String name;

    @JsonProperty
    @Size(min = 7, max = 9, message="O CEP deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String cep;

    @JsonProperty
    @Size(min = 3, max = 20, message="A Zona deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String zone;
}
