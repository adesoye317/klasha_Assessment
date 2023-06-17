package com.klasha.assessment.model.response.city_state;

import lombok.Data;

import java.util.List;

@Data
public class StateandCittiesResponse {

    private String state;
    private List<String> cities;
}
