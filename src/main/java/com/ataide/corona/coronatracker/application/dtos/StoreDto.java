package com.ataide.corona.coronatracker.application.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto implements ProfileType{

    private Long id;

    @NotNull(message = "O nome deve ser informado.")
    @Size(min = 3, max = 50, message="O nome deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String name;

    @NotNull(message = "O telefone deve ser informado.")
    @Size(min = 10, max = 11, message="O telefone deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String phone;

    @NotNull(message = "O cnpj deve ser informado.")
    @Size(min = 14, max = 14, message="O CNPJ deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    private String cnpj;

    @NotNull(message = "O CEP precisa ser preenchido.")
    private String cep;

}
