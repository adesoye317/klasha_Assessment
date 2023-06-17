package com.klasha.assessment.service;

import com.klasha.assessment.model.response.get_cities.GetTopCitiesResponse;
import org.springframework.stereotype.Component;


public interface GetTopCitiesInterface {
    GetTopCitiesResponse getTopCities(int numberCities, String country);
}
