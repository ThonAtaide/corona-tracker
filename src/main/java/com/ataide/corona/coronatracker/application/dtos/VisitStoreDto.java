package com.ataide.corona.coronatracker.application.dtos;

import com.ataide.corona.coronatracker.application.dtos.response.RecordData;
import com.ataide.corona.coronatracker.domain.entities.VisitedStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitStoreDto implements RecordData {
    private Long id;
    private String name;
    private String phone;

    public VisitStoreDto(VisitedStore visitCreated) {
        this.id = visitCreated.getId();
        this.name = visitCreated.getName();
        this.phone = visitCreated.getPhone();
    }
}
