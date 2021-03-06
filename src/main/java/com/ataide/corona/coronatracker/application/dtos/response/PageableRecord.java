package com.ataide.corona.coronatracker.application.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageableRecord implements RecordData {
    @JsonProperty
    private Collection<RecordData> collection;
    @JsonProperty
    private Long currentPage;
    @JsonProperty
    private Long pageSize;
    @JsonProperty
    private Long totalElements;
    @JsonProperty
    private Long totalPages;
}
