package com.klasha.assessment.model.request.get_cities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetCityandStateRequest {

    private String state;
    private String country;
}
