package com.ataide.corona.coronatracker.application.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageableRecord implements Record {
    private Collection<Record> collection;
    private Long currentPage;
    private Long pageSize;
    private Long total;
}
