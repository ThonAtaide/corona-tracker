package com.ataide.corona.coronatracker.application.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @JsonCreator)
public class UserLoginDto {

    @NotNull(message = "O username deve ser informado.")
    @Size(min = 6, max = 32, message="O username deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String username;

    @NotNull(message = "O password deve ser informado.")
    @Size(min = 6, max = 32, message="O password deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String password;
}
