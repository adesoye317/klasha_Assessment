package com.klasha.assessment.service;

import com.klasha.assessment.model.get_cities.response.GetTopCitiesResponse;

public interface GetTopCitiesInterface {
    GetTopCitiesResponse getTopCities(String numberCities, String country);
}
