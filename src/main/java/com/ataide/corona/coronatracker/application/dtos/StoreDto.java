package com.ataide.corona.coronatracker.application.dtos;

import com.ataide.corona.coronatracker.application.dtos.response.RecordData;
import com.ataide.corona.coronatracker.domain.entities.Store;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StoreDto implements ProfileType, RecordData {

    @JsonProperty
    private Long id;

    @NotNull(message = "O nome deve ser informado.")
    @Size(min = 3, max = 50, message="O nome deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    @JsonProperty
    private String name;

    @NotNull(message = "O telefone deve ser informado.")
    @Size(min = 10, max = 11, message="O telefone deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    @JsonProperty
    private String phone;

    @NotNull(message = "O cnpj deve ser informado.")
    @Size(min = 14, max = 14, message="O CNPJ deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    @JsonProperty
    private String cnpj;

    @NotNull(message = "O CEP precisa ser preenchido.")
    @Size(min = 7, max = 9, message="O CEP deve ter no máximo {max} caracteres e no minimo {min} caracteres. Você digitou: ${validatedValue}" )
    @JsonProperty
    private String cep;

    public StoreDto(Store store) {
        this.id = store.getId();
        this.name = store.getName();
        this.phone = store.getPhone();
        this.cnpj = store.getCnpj();
        this.cep = store.getCep();
    }
}
