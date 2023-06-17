package com.klasha.assessment.model.response.city_state;

import lombok.Data;

import java.util.List;

@Data
public class GetStateandCitiesResponse {
    private String country;
    private List<StateandCittiesResponse> stateCities;
}
