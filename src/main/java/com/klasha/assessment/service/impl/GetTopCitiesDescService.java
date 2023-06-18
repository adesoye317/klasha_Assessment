package com.klasha.assessment.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.klasha.assessment.model.request.get_cities.GetTopCitiesRequest;
import com.klasha.assessment.model.response.get_cities.GetTopCitiesResponse;
import com.klasha.assessment.service.GetTopCitiesInterface;
import com.klasha.assessment.util.HTTPUTILL;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author adegokeadesoye
 * @apiNote This service is for Getting top 10 cities population
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class GetTopCitiesDescService implements GetTopCitiesInterface {
    private final Environment env;
    private final HTTPUTILL httputill;

    @Override
    public GetTopCitiesResponse getTopCities(int numberCities, String country){

        GetTopCitiesResponse response = null;
        try {
            //Creating an Instance of the Get Population Request
            GetTopCitiesRequest request = new GetTopCitiesRequest();
            request.setCountry(country);
            request.setLimit(numberCities);
            request.setOrder(env.getProperty("population.order"));
            request.setOrderBy(env.getProperty("population.orderby"));

            //Making the HTTP Call
            String url = env.getProperty("get.population.cities.url");
            log.info("THE URL CALLED FOR GET POPULATION CITIES::{}", url);
            String responseBody = httputill.post(request, url);

            log.info("THE RESPONSE::{}", responseBody);
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(responseBody, GetTopCitiesResponse.class);
        } catch (Exception e) {
            log.info(e.getMessage());
            log.info(Arrays.toString(e.getStackTrace()).replaceAll(", ", "\n"));
        }
        return response;
    }

}
