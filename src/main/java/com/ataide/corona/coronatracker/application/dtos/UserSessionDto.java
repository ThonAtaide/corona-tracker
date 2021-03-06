package com.ataide.corona.coronatracker.application.dtos;

import com.ataide.corona.coronatracker.application.dtos.response.RecordData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserSessionDto implements RecordData {
    private Long id;
    private String name;
    private String userType;
    private String jwtToken;
}
