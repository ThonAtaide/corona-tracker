package com.ataide.corona.coronatracker.application.dtos;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        defaultImpl = StoreDto.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = StoreDto.class, name = "store"),
})
public interface ProfileType {
}
